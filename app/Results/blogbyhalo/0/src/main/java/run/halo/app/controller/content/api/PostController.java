package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.BaseCommentDTO;
import run.halo.app.model.dto.post.BasePostSimpleDTO;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.PostCommentParam;
import run.halo.app.model.vo.BaseCommentVO;
import run.halo.app.model.vo.BaseCommentWithParentVO;
import run.halo.app.model.vo.CommentWithHasChildrenVO;
import run.halo.app.model.vo.PostDetailVO;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCommentService;
import run.halo.app.service.PostService;
import run.halo.app.Interface.PostService;
import run.halo.app.Interface.OptionService;
@RestController("ApiContentPostController")
@RequestMapping("/api/content/posts")
public class PostController {

 private  PostService postService;

 private  PostCommentService postCommentService;

 private  OptionService optionService;

public PostController(PostService postService, PostCommentService postCommentService, OptionService optionService) {
    this.postService = postService;
    this.postCommentService = postCommentService;
    this.optionService = optionService;
}
@PostMapping(value = "search")
@ApiOperation("Lists posts by keyword")
public Page<BasePostSimpleDTO> pageBy(String keyword,Pageable pageable){
    Page<Post> postPage = postService.pageBy(keyword, pageable);
    return postService.convertToSimple(postPage);
}


@GetMapping("{postId:\\d+}/comments/top_view")
public Page<CommentWithHasChildrenVO> listTopComments(Integer postId,int page,Sort sort){
    return postCommentService.pageTopCommentsBy(postId, CommentStatus.PUBLISHED, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@GetMapping("{postId:\\d+}/comments/list_view")
@ApiOperation("Lists comment with list view")
public Page<BaseCommentWithParentVO> listComments(Integer postId,int page,Sort sort){
    return postCommentService.pageWithParentVoBy(postId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@GetMapping("{postId:\\d+}/next")
@ApiOperation("Gets next post by current post id.")
public PostDetailVO getNextPostBy(Integer postId){
    Post post = postService.getById(postId);
    Post nextPost = postService.getNextPost(post).orElseThrow(() -> new NotFoundException("查询不到该文章的信息"));
    return postService.convertToDetailVo(nextPost);
}


@PostMapping("{postId:\\d+}/likes")
@ApiOperation("Likes a post")
@CacheLock(autoDelete = false, traceRequest = true)
public void like(Integer postId){
    postService.increaseLike(postId);
}


@GetMapping("/slug")
@ApiOperation("Gets a post")
public PostDetailVO getBy(String slug,Boolean formatDisabled,Boolean sourceDisabled){
    PostDetailVO postDetailVO = postService.convertToDetailVo(postService.getBySlug(slug));
    if (formatDisabled) {
        // Clear the format content
        postDetailVO.setFormatContent(null);
    }
    if (sourceDisabled) {
        // Clear the original content
        postDetailVO.setOriginalContent(null);
    }
    postService.publishVisitEvent(postDetailVO.getId());
    return postDetailVO;
}


@GetMapping("{postId:\\d+}/prev")
@ApiOperation("Gets previous post by current post id.")
public PostDetailVO getPrevPostBy(Integer postId){
    Post post = postService.getById(postId);
    Post prevPost = postService.getPrevPost(post).orElseThrow(() -> new NotFoundException("查询不到该文章的信息"));
    return postService.convertToDetailVo(prevPost);
}


@PostMapping("comments")
@ApiOperation("Comments a post")
@CacheLock(autoDelete = false, traceRequest = true)
public BaseCommentDTO comment(PostCommentParam postCommentParam){
    postCommentService.validateCommentBlackListStatus();
    // Escape content
    postCommentParam.setContent(HtmlUtils.htmlEscape(postCommentParam.getContent(), StandardCharsets.UTF_8.displayName()));
    return postCommentService.convertTo(postCommentService.createBy(postCommentParam));
}


@GetMapping("{postId:\\d+}/comments/tree_view")
@ApiOperation("Lists comments with tree view")
public Page<BaseCommentVO> listCommentsTree(Integer postId,int page,Sort sort){
    return postCommentService.pageVosBy(postId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@GetMapping("{postId:\\d+}/comments/{commentParentId:\\d+}/children")
public List<BaseCommentDTO> listChildrenBy(Integer postId,Long commentParentId,Sort sort){
    // Find all children comments
    List<PostComment> postComments = postCommentService.listChildrenBy(postId, commentParentId, CommentStatus.PUBLISHED, sort);
    // Convert to base comment dto
    return postCommentService.convertTo(postComments);
}


}