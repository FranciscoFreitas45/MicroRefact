package run.halo.app.service.base;
 import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.dto.BaseCommentDTO;
import run.halo.app.model.entity.BaseComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.params.BaseCommentParam;
import run.halo.app.model.params.CommentQuery;
import run.halo.app.model.vo.BaseCommentVO;
import run.halo.app.model.vo.BaseCommentWithParentVO;
import run.halo.app.model.vo.CommentWithHasChildrenVO;
public interface BaseCommentService extends CrudService<COMMENT, Long>{


@NonNull
public Page<COMMENT> pageBy(CommentQuery commentQuery,Pageable pageable)
;

public long countByStatus(CommentStatus status)
;

@NonNull
public Page<BaseCommentVO> pageVosAllBy(Integer postId,Pageable pageable)
;

public List<BaseCommentVO> convertToVo(List<COMMENT> comments,Comparator<BaseCommentVO> comparator)
;

public List<COMMENT> removeByPostId(Integer postId)
;

@NonNull
public Page<BaseCommentDTO> convertTo(Page<COMMENT> commentPage)
;

@NonNull
public Page<CommentWithHasChildrenVO> pageTopCommentsBy(Integer targetId,CommentStatus status,Pageable pageable)
;

@NonNull
public Page<COMMENT> pageLatest(int top,CommentStatus status)
;

@NonNull
public List<COMMENT> listChildrenBy(Integer targetId,Long commentParentId,Sort sort)
;

@NonNull
public Page<BaseCommentVO> pageVosBy(List<COMMENT> comments,Pageable pageable)
;

@NonNull
public COMMENT createBy(BaseCommentParam<COMMENT> commentParam)
;

public Map<Integer,Long> countByStatusAndPostIds(CommentStatus status,Collection<Integer> postIds)
;

@NonNull
public List<COMMENT> removeByIds(Collection<Long> ids)
;

@NonNull
public COMMENT updateStatus(Long commentId,CommentStatus status)
;

@NonNull
public Map<Integer,Long> countByPostIds(Collection<Integer> postIds)
;

@NonNull
@Override
public COMMENT create(COMMENT comment)
;

public void validateTarget(Integer targetId)
;

@NonNull
public List<COMMENT> listBy(Integer postId)
;

@NonNull
public Page<BaseCommentWithParentVO> pageWithParentVoBy(Integer postId,Pageable pageable)
;

@NonNull
public List<COMMENT> updateStatusByIds(List<Long> ids,CommentStatus status)
;

public long countByPostId(Integer postId)
;

}