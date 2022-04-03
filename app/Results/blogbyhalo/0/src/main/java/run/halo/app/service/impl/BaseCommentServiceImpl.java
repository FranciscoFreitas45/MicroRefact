package run.halo.app.service.impl;
 import org.springframework.data.domain.Sort.Direction.DESC;
import cn.hutool.core.util.URLUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.event.comment.CommentNewEvent;
import run.halo.app.event.comment.CommentReplyEvent;
import run.halo.app.exception.BadRequestException;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.BaseCommentDTO;
import run.halo.app.model.entity.BaseComment;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.params.BaseCommentParam;
import run.halo.app.model.params.CommentQuery;
import run.halo.app.model.projection.CommentChildrenCountProjection;
import run.halo.app.model.projection.CommentCountProjection;
import run.halo.app.model.properties.BlogProperties;
import run.halo.app.model.properties.CommentProperties;
import run.halo.app.model.support.CommentPage;
import run.halo.app.model.vo.BaseCommentVO;
import run.halo.app.model.vo.BaseCommentWithParentVO;
import run.halo.app.model.vo.CommentWithHasChildrenVO;
import run.halo.app.repository.base.BaseCommentRepository;
import run.halo.app.security.authentication.Authentication;
import run.halo.app.security.context.SecurityContextHolder;
import run.halo.app.service.OptionService;
import run.halo.app.service.UserService;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.service.base.BaseCommentService;
import run.halo.app.utils.ServiceUtils;
import run.halo.app.utils.ServletUtils;
import run.halo.app.utils.ValidationUtils;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.UserService;
import run.halo.app.DTO.Authentication;
@Slf4j
public class BaseCommentServiceImpl extends AbstractCrudService<COMMENT, Long>implements BaseCommentService<COMMENT>{

 protected  OptionService optionService;

 protected  UserService userService;

 protected  ApplicationEventPublisher eventPublisher;

 private  BaseCommentRepository<COMMENT> baseCommentRepository;

public BaseCommentServiceImpl(BaseCommentRepository<COMMENT> baseCommentRepository, OptionService optionService, UserService userService, ApplicationEventPublisher eventPublisher) {
    super(baseCommentRepository);
    this.baseCommentRepository = baseCommentRepository;
    this.optionService = optionService;
    this.userService = userService;
    this.eventPublisher = eventPublisher;
}
@Override
public long countByStatus(CommentStatus status){
    return baseCommentRepository.countByStatus(status);
}


@Override
@NonNull
public COMMENT removeById(Long id){
    Assert.notNull(id, "Comment id must not be null");
    COMMENT comment = baseCommentRepository.findById(id).orElseThrow(() -> new NotFoundException("查询不到该评论的信息").setErrorData(id));
    List<COMMENT> children = listChildrenBy(comment.getPostId(), id, Sort.by(DESC, "createTime"));
    if (children.size() > 0) {
        children.forEach(child -> {
            super.removeById(child.getId());
        });
    }
    return super.removeById(id);
}


@NonNull
@Override
public List<BaseCommentVO> convertToVo(List<COMMENT> comments,Comparator<BaseCommentVO> comparator){
    if (CollectionUtils.isEmpty(comments)) {
        return Collections.emptyList();
    }
    // Init the top virtual comment
    BaseCommentVO topVirtualComment = new BaseCommentVO();
    topVirtualComment.setId(0L);
    topVirtualComment.setChildren(new LinkedList<>());
    // Concrete the comment tree
    concreteTree(topVirtualComment, new LinkedList<>(comments), comparator);
    return topVirtualComment.getChildren();
}


@Override
@NonNull
public Page<CommentWithHasChildrenVO> pageTopCommentsBy(Integer targetId,CommentStatus status,Pageable pageable){
    Assert.notNull(targetId, "Target id must not be null");
    Assert.notNull(status, "Comment status must not be null");
    Assert.notNull(pageable, "Page info must not be null");
    // Get all comments
    Page<COMMENT> topCommentPage = baseCommentRepository.findAllByPostIdAndStatusAndParentId(targetId, status, 0L, pageable);
    if (topCommentPage.isEmpty()) {
        // If the comments is empty
        return ServiceUtils.buildEmptyPageImpl(topCommentPage);
    }
    // Get top comment ids
    Set<Long> topCommentIds = ServiceUtils.fetchProperty(topCommentPage.getContent(), BaseComment::getId);
    // Get direct children count
    List<CommentChildrenCountProjection> directChildrenCount = baseCommentRepository.findDirectChildrenCount(topCommentIds, CommentStatus.PUBLISHED);
    // Convert to comment - children count map
    Map<Long, Long> commentChildrenCountMap = ServiceUtils.convertToMap(directChildrenCount, CommentChildrenCountProjection::getCommentId, CommentChildrenCountProjection::getDirectChildrenCount);
    // Convert to comment with has children vo
    return topCommentPage.map(topComment -> {
        CommentWithHasChildrenVO comment = new CommentWithHasChildrenVO().convertFrom(topComment);
        comment.setHasChildren(commentChildrenCountMap.getOrDefault(topComment.getId(), 0L) > 0);
        comment.setAvatar(buildAvatarUrl(topComment.getGravatarMd5()));
        return comment;
    });
}


@Override
@NonNull
public Page<COMMENT> pageLatest(int top,CommentStatus status){
    if (status == null) {
        return listAll(ServiceUtils.buildLatestPageable(top));
    }
    return baseCommentRepository.findAllByStatus(status, ServiceUtils.buildLatestPageable(top));
}


public Comparator<BaseCommentVO> buildCommentComparator(Sort sort){
    return (currentComment, toCompareComment) -> {
        Assert.notNull(currentComment, "Current comment must not be null");
        Assert.notNull(toCompareComment, "Comment to compare must not be null");
        // Get sort order
        Sort.Order order = sort.filter(anOrder -> "id".equals(anOrder.getProperty())).get().findFirst().orElseGet(() -> Sort.Order.desc("id"));
        // Init sign
        int sign = order.getDirection().isAscending() ? 1 : -1;
        // Compare id property
        return sign * currentComment.getId().compareTo(toCompareComment.getId());
    };
}


@Override
@NonNull
public List<COMMENT> removeByIds(Collection<Long> ids){
    if (CollectionUtils.isEmpty(ids)) {
        return Collections.emptyList();
    }
    return ids.stream().map(this::removeById).collect(Collectors.toList());
}


@Override
@NonNull
public Map<Integer,Long> countByPostIds(Collection<Integer> postIds){
    if (CollectionUtils.isEmpty(postIds)) {
        return Collections.emptyMap();
    }
    // Get all comment counts
    List<CommentCountProjection> commentCountProjections = baseCommentRepository.countByPostIds(postIds);
    return ServiceUtils.convertToMap(commentCountProjections, CommentCountProjection::getPostId, CommentCountProjection::getCount);
}


@Override
@NonNull
public COMMENT create(COMMENT comment){
    Assert.notNull(comment, "Domain must not be null");
    // Check post id
    if (!ServiceUtils.isEmptyId(comment.getPostId())) {
        validateTarget(comment.getPostId());
    }
    // Check parent id
    if (!ServiceUtils.isEmptyId(comment.getParentId())) {
        mustExistById(comment.getParentId());
    }
    // Check user login status and set this field
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // Set some default values
    if (comment.getIpAddress() == null) {
        comment.setIpAddress(ServletUtils.getRequestIp());
    }
    if (comment.getUserAgent() == null) {
        comment.setUserAgent(ServletUtils.getHeaderIgnoreCase(HttpHeaders.USER_AGENT));
    }
    if (comment.getGravatarMd5() == null) {
        comment.setGravatarMd5(DigestUtils.md5Hex(comment.getEmail()));
    }
    if (StringUtils.isNotEmpty(comment.getAuthorUrl())) {
        comment.setAuthorUrl(URLUtil.normalize(comment.getAuthorUrl()));
    }
    if (authentication != null) {
        // Comment of blogger
        comment.setIsAdmin(true);
        comment.setStatus(CommentStatus.PUBLISHED);
    } else {
        // Comment of guest
        // Handle comment status
        Boolean needAudit = optionService.getByPropertyOrDefault(CommentProperties.NEW_NEED_CHECK, Boolean.class, true);
        comment.setStatus(needAudit ? CommentStatus.AUDITING : CommentStatus.PUBLISHED);
    }
    // Create comment
    COMMENT createdComment = super.create(comment);
    if (ServiceUtils.isEmptyId(createdComment.getParentId())) {
        if (authentication == null) {
            // New comment of guest
            eventPublisher.publishEvent(new CommentNewEvent(this, createdComment.getId()));
        }
    } else {
        // Reply comment
        eventPublisher.publishEvent(new CommentReplyEvent(this, createdComment.getId()));
    }
    return createdComment;
}


@Override
@NonNull
public List<COMMENT> listBy(Integer postId){
    Assert.notNull(postId, "Post id must not be null");
    return baseCommentRepository.findAllByPostId(postId);
}


@Override
@NonNull
public List<COMMENT> updateStatusByIds(List<Long> ids,CommentStatus status){
    if (CollectionUtils.isEmpty(ids)) {
        return Collections.emptyList();
    }
    return ids.stream().map(id -> {
        return updateStatus(id, status);
    }).collect(Collectors.toList());
}


@Override
@NonNull
public Page<COMMENT> pageBy(CommentQuery commentQuery,Pageable pageable){
    Assert.notNull(pageable, "Page info must not be null");
    return baseCommentRepository.findAll(buildSpecByQuery(commentQuery), pageable);
}


public void getChildrenRecursively(List<COMMENT> topComments,Set<COMMENT> children){
    Assert.notNull(children, "Children comment set must not be null");
    if (CollectionUtils.isEmpty(topComments)) {
        return;
    }
    // Convert comment id set
    Set<Long> commentIds = ServiceUtils.fetchProperty(topComments, COMMENT::getId);
    // Get direct children
    List<COMMENT> directChildren = baseCommentRepository.findAllByParentIdIn(commentIds);
    // Recursively invoke
    getChildrenRecursively(directChildren, children);
    // Add direct children to children result
    children.addAll(topComments);
}


@Override
@NonNull
public Page<BaseCommentVO> pageVosAllBy(Integer postId,Pageable pageable){
    Assert.notNull(postId, "Post id must not be null");
    Assert.notNull(pageable, "Page info must not be null");
    log.debug("Getting comment tree view of post: [{}], page info: [{}]", postId, pageable);
    // List all the top comments (Caution: This list will be cleared)
    List<COMMENT> comments = baseCommentRepository.findAllByPostId(postId);
    return pageVosBy(comments, pageable);
}


public void concreteTree(BaseCommentVO parentComment,Collection<COMMENT> comments,Comparator<BaseCommentVO> commentComparator){
    Assert.notNull(parentComment, "Parent comment must not be null");
    if (CollectionUtils.isEmpty(comments)) {
        return;
    }
    // Get children
    List<COMMENT> children = comments.stream().filter(comment -> Objects.equals(parentComment.getId(), comment.getParentId())).collect(Collectors.toList());
    // Add children
    children.forEach(comment -> {
        // Convert to comment vo
        BaseCommentVO commentVo = new BaseCommentVO().convertFrom(comment);
        commentVo.setAvatar(buildAvatarUrl(commentVo.getGravatarMd5()));
        if (parentComment.getChildren() == null) {
            parentComment.setChildren(new LinkedList<>());
        }
        parentComment.getChildren().add(commentVo);
    });
    // Remove children
    comments.removeAll(children);
    if (!CollectionUtils.isEmpty(parentComment.getChildren())) {
        // Recursively concrete the children
        parentComment.getChildren().forEach(childComment -> concreteTree(childComment, comments, commentComparator));
        // Sort the children
        if (commentComparator != null) {
            parentComment.getChildren().sort(commentComparator);
        }
    }
}


@Override
public List<COMMENT> removeByPostId(Integer postId){
    Assert.notNull(postId, "Post id must not be null");
    return baseCommentRepository.deleteByPostId(postId);
}


@Override
@NonNull
public BaseCommentDTO convertTo(COMMENT comment){
    Assert.notNull(comment, "Comment must not be null");
    BaseCommentDTO baseCommentDto = new BaseCommentDTO().convertFrom(comment);
    baseCommentDto.setAvatar(buildAvatarUrl(comment.getGravatarMd5()));
    return baseCommentDto;
}


@Override
@NonNull
public List<COMMENT> listChildrenBy(Integer targetId,Long commentParentId,Sort sort){
    Assert.notNull(targetId, "Target id must not be null");
    Assert.notNull(commentParentId, "Comment parent id must not be null");
    Assert.notNull(sort, "Sort info must not be null");
    // Get comments recursively
    // Get direct children
    List<COMMENT> directChildren = baseCommentRepository.findAllByPostIdAndParentId(targetId, commentParentId);
    // Create result container
    Set<COMMENT> children = new HashSet<>();
    // Get children comments
    getChildrenRecursively(directChildren, children);
    // Sort children
    List<COMMENT> childrenList = new ArrayList<>(children);
    childrenList.sort(Comparator.comparing(BaseComment::getId));
    return childrenList;
}


@Override
@NonNull
public Page<BaseCommentVO> pageVosBy(Integer postId,Pageable pageable){
    Assert.notNull(postId, "Post id must not be null");
    Assert.notNull(pageable, "Page info must not be null");
    log.debug("Getting comment tree view of post: [{}], page info: [{}]", postId, pageable);
    // List all the top comments (Caution: This list will be cleared)
    List<COMMENT> comments = baseCommentRepository.findAllByPostIdAndStatus(postId, CommentStatus.PUBLISHED);
    return pageVosBy(comments, pageable);
}


@Override
@NonNull
public COMMENT createBy(BaseCommentParam<COMMENT> commentParam){
    Assert.notNull(commentParam, "Comment param must not be null");
    // Check user login status and set this field
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        // Blogger comment
        User user = authentication.getDetail().getUser();
        commentParam.setAuthor(StringUtils.isBlank(user.getNickname()) ? user.getUsername() : user.getNickname());
        commentParam.setEmail(user.getEmail());
        commentParam.setAuthorUrl(optionService.getByPropertyOrDefault(BlogProperties.BLOG_URL, String.class, null));
    }
    // Validate the comment param manually
    ValidationUtils.validate(commentParam);
    if (authentication == null) {
        // Anonymous comment
        // Check email
        if (userService.getByEmail(commentParam.getEmail()).isPresent()) {
            throw new BadRequestException("不能使用博主的邮箱，如果您是博主，请登录管理端进行回复。");
        }
    }
    // Convert to comment
    return create(commentParam.convertTo());
}


@Override
public Map<Integer,Long> countByStatusAndPostIds(CommentStatus status,Collection<Integer> postIds){
    if (CollectionUtils.isEmpty(postIds)) {
        return Collections.emptyMap();
    }
    // Get all comment counts
    List<CommentCountProjection> commentCountProjections = baseCommentRepository.countByStatusAndPostIds(status, postIds);
    return ServiceUtils.convertToMap(commentCountProjections, CommentCountProjection::getPostId, CommentCountProjection::getCount);
}


@Override
@NonNull
public COMMENT updateStatus(Long commentId,CommentStatus status){
    Assert.notNull(commentId, "Comment id must not be null");
    Assert.notNull(status, "Comment status must not be null");
    // Get comment by id
    COMMENT comment = getById(commentId);
    // Set comment status
    comment.setStatus(status);
    // Update comment
    return update(comment);
}


@NonNull
public Specification<COMMENT> buildSpecByQuery(CommentQuery commentQuery){
    Assert.notNull(commentQuery, "Comment query must not be null");
    return (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new LinkedList<>();
        if (commentQuery.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), commentQuery.getStatus()));
        }
        if (commentQuery.getKeyword() != null) {
            String likeCondition = String.format("%%%s%%", StringUtils.strip(commentQuery.getKeyword()));
            Predicate authorLike = criteriaBuilder.like(root.get("author"), likeCondition);
            Predicate contentLike = criteriaBuilder.like(root.get("content"), likeCondition);
            Predicate emailLike = criteriaBuilder.like(root.get("email"), likeCondition);
            predicates.add(criteriaBuilder.or(authorLike, contentLike, emailLike));
        }
        return query.where(predicates.toArray(new Predicate[0])).getRestriction();
    };
}


@Override
@NonNull
public Page<BaseCommentWithParentVO> pageWithParentVoBy(Integer postId,Pageable pageable){
    Assert.notNull(postId, "Post id must not be null");
    Assert.notNull(pageable, "Page info must not be null");
    log.debug("Getting comment list view of post: [{}], page info: [{}]", postId, pageable);
    // List all the top comments (Caution: This list will be cleared)
    Page<COMMENT> commentPage = baseCommentRepository.findAllByPostIdAndStatus(postId, CommentStatus.PUBLISHED, pageable);
    // Get all comments
    List<COMMENT> comments = commentPage.getContent();
    // Get all comment parent ids
    Set<Long> parentIds = ServiceUtils.fetchProperty(comments, COMMENT::getParentId);
    // Get all parent comments
    List<COMMENT> parentComments = baseCommentRepository.findAllByIdIn(parentIds, pageable.getSort());
    // Convert to comment map (Key: comment id, value: comment)
    Map<Long, COMMENT> parentCommentMap = ServiceUtils.convertToMap(parentComments, COMMENT::getId);
    Map<Long, BaseCommentWithParentVO> parentCommentVoMap = new HashMap<>(parentCommentMap.size());
    // Convert to comment page
    return commentPage.map(comment -> {
        // Convert to with parent vo
        BaseCommentWithParentVO commentWithParentVo = new BaseCommentWithParentVO().convertFrom(comment);
        commentWithParentVo.setAvatar(buildAvatarUrl(commentWithParentVo.getGravatarMd5()));
        // Get parent comment vo from cache
        BaseCommentWithParentVO parentCommentVo = parentCommentVoMap.get(comment.getParentId());
        if (parentCommentVo == null) {
            // Get parent comment
            COMMENT parentComment = parentCommentMap.get(comment.getParentId());
            if (parentComment != null) {
                // Convert to parent comment vo
                parentCommentVo = new BaseCommentWithParentVO().convertFrom(parentComment);
                parentCommentVo.setAvatar(buildAvatarUrl(parentComment.getGravatarMd5()));
                // Cache the parent comment vo
                parentCommentVoMap.put(parentComment.getId(), parentCommentVo);
            }
        }
        // Set parent
        commentWithParentVo.setParent(parentCommentVo == null ? null : parentCommentVo.clone());
        return commentWithParentVo;
    });
}


@Override
public long countByPostId(Integer postId){
    Assert.notNull(postId, "Post id must not be null");
    return baseCommentRepository.countByPostId(postId);
}


public String buildAvatarUrl(String gravatarMd5){
    final String gravatarSource = optionService.getByPropertyOrDefault(CommentProperties.GRAVATAR_SOURCE, String.class);
    final String gravatarDefault = optionService.getByPropertyOrDefault(CommentProperties.GRAVATAR_DEFAULT, String.class);
    return gravatarSource + gravatarMd5 + "?d=" + gravatarDefault;
}


}