package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.BaseCommentDTO;
import run.halo.app.model.entity.PostComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.params.CommentQuery;
import run.halo.app.model.params.PostCommentParam;
import run.halo.app.model.vo.BaseCommentVO;
import run.halo.app.model.vo.BaseCommentWithParentVO;
import run.halo.app.model.vo.PostCommentWithPostVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCommentService;
import run.halo.app.Interface.OptionService;
@RestController
@RequestMapping("/api/admin/posts/comments")
public class PostCommentController {

 private  PostCommentService postCommentService;

 private  OptionService optionService;

public PostCommentController(PostCommentService postCommentService, OptionService optionService) {
    this.postCommentService = postCommentService;
    this.optionService = optionService;
}
@GetMapping
@ApiOperation("Lists post comments")
public Page<PostCommentWithPostVO> pageBy(Pageable pageable,CommentQuery commentQuery){
    Page<PostComment> commentPage = postCommentService.pageBy(commentQuery, pageable);
    return postCommentService.convertToWithPostVo(commentPage);
}


@GetMapping("latest")
@ApiOperation("Pages post latest comments")
public List<PostCommentWithPostVO> listLatest(int top,CommentStatus status){
    // Get latest comment
    List<PostComment> content = postCommentService.pageLatest(top, status).getContent();
    // Convert and return
    return postCommentService.convertToWithPostVo(content);
}


@GetMapping("{postId:\\d+}/tree_view")
@ApiOperation("Lists post comments with tree view")
public Page<BaseCommentVO> listCommentTree(Integer postId,int page,Sort sort){
    return postCommentService.pageVosAllBy(postId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@PostMapping
@ApiOperation("Creates a post comment (new or reply)")
public BaseCommentDTO createBy(PostCommentParam postCommentParam){
    PostComment createdPostComment = postCommentService.createBy(postCommentParam);
    return postCommentService.convertTo(createdPostComment);
}


@GetMapping("{postId:\\d+}/list_view")
@ApiOperation("Lists post comment with list view")
public Page<BaseCommentWithParentVO> listComments(Integer postId,int page,Sort sort){
    return postCommentService.pageWithParentVoBy(postId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@PutMapping("{commentId:\\d+}/status/{status}")
@ApiOperation("Updates post comment status")
public BaseCommentDTO updateStatusBy(Long commentId,CommentStatus status){
    // Update comment status
    PostComment updatedPostComment = postCommentService.updateStatus(commentId, status);
    return postCommentService.convertTo(updatedPostComment);
}


@PutMapping("{commentId:\\d+}")
@ApiOperation("Updates a post comment")
public BaseCommentDTO updateBy(PostCommentParam commentParam,Long commentId){
    PostComment commentToUpdate = postCommentService.getById(commentId);
    commentParam.update(commentToUpdate);
    return postCommentService.convertTo(postCommentService.update(commentToUpdate));
}


@GetMapping("{commentId:\\d+}")
@ApiOperation("Gets a post comment by comment id")
public PostCommentWithPostVO getBy(Long commentId){
    PostComment comment = postCommentService.getById(commentId);
    return postCommentService.convertToWithPostVo(comment);
}


@DeleteMapping
@ApiOperation("Delete post comments permanently in batch by id array")
public List<PostComment> deletePermanentlyInBatch(List<Long> ids){
    return postCommentService.removeByIds(ids);
}


@PutMapping("status/{status}")
@ApiOperation("Updates post comment status in batch")
public List<BaseCommentDTO> updateStatusInBatch(CommentStatus status,List<Long> ids){
    List<PostComment> comments = postCommentService.updateStatusByIds(ids, status);
    return postCommentService.convertTo(comments);
}


@DeleteMapping("{commentId:\\d+}")
@ApiOperation("Deletes post comment permanently and recursively")
public BaseCommentDTO deletePermanently(Long commentId){
    PostComment deletedPostComment = postCommentService.removeById(commentId);
    return postCommentService.convertTo(deletedPostComment);
}


}