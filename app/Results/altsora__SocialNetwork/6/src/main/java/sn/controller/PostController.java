package sn.controller;
 import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import sn.api.requests.PostCommentCreateRequest;
import sn.api.requests.PostEditRequest;
import sn.api.response;
import sn.service.CommentService;
import sn.service.PostService;
@RestController
@RequestMapping("/post")
public class PostController {

 private  PostService postService;

 private  CommentService commentService;

@Autowired
public PostController(PostService postService, CommentService commentService) {
    this.postService = postService;
    this.commentService = commentService;
}
@DeleteMapping("/{id}")
public ServiceResponse<AbstractResponse> deletePost(long id){
    IdResponse deletePost = postService.deletePost(id);
    ServiceResponse response = new ServiceResponse(deletePost);
    return response;
}


@PutMapping("/{id}")
public ServiceResponse<AbstractResponse> editPost(long id,long publishDate,PostEditRequest postEditRequest){
    PostResponse post = postService.editPost(id, publishDate, postEditRequest);
    ServiceResponse response = new ServiceResponse(post);
    return response;
}


@DeleteMapping("/{id}/comments/{comment_id}")
public SimpleServiceResponse<IdResponse> deleteComment(long id,long commentId){
    return new SimpleServiceResponse<>(postService.deleteComment(id, commentId));
}


@PostMapping("/{id}/report")
public ServiceResponse<AbstractResponse> postComplaint(long id){
    MessageResponse complaintPost = postService.complaintPost(id);
    ServiceResponse response = new ServiceResponse(complaintPost);
    return response;
}


@PutMapping("/{id}/comments/{comment_id}")
public SimpleServiceResponse<CommentResponse> editComment(long id,long commentId,PostCommentCreateRequest postCommentCreateRequest){
    return new SimpleServiceResponse<>(postService.editComment(id, commentId, postCommentCreateRequest));
}


@GetMapping
public ServiceResponse<AbstractResponse> findPosts(String text,long dateFrom,long dateTo,int offset,int itemPerPage){
    List<PostResponse> posts = postService.findPosts(text, dateFrom, dateTo, offset, itemPerPage);
    PostListResponse postListResponse = new PostListResponse(posts);
    return new ServiceResponse(posts.size(), offset, itemPerPage, postListResponse);
}


@GetMapping("/{id}/comments")
public ServiceResponseDataList<CommentResponse> getComments(long id,int offset,int initPerPage){
    return new ServiceResponseDataList<>(commentService.getCommentsByPostId(id).size(), offset, initPerPage, commentService.getCommentsByPostId(id));
}


@GetMapping("/{id}")
public ServiceResponse<AbstractResponse> findPostById(long id){
    PostResponse post = postService.findPostById(id);
    ServiceResponse response = new ServiceResponse(post);
    return response;
}


@PutMapping("/{id}/recover")
public ServiceResponse<AbstractResponse> recoverPost(long id){
    PostResponse post = postService.recoverPost(id);
    ServiceResponse response = new ServiceResponse(post);
    return response;
}


@PutMapping("/{id}/comments/{comment_id}/recover")
public SimpleServiceResponse<CommentResponse> recoverComment(long id,long commentId){
    return new SimpleServiceResponse<>(postService.recoverComment(id, commentId));
}


@PostMapping("/{id}/comments/{commentId}/report")
public ServiceResponse<AbstractResponse> commentComplaint(long id,long commentId){
    MessageResponse complaintPost = postService.complaintComment(id, commentId);
    ServiceResponse response = new ServiceResponse(complaintPost);
    return response;
}


@PostMapping("/{id}/comments")
public SimpleServiceResponse<CommentResponse> createComment(long id,PostCommentCreateRequest postCommentCreateRequest){
    return new SimpleServiceResponse<>(postService.createPostComment(id, postCommentCreateRequest));
}


}