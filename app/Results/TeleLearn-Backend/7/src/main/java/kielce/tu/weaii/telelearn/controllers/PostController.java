package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.requests.courses.PostCommentRequest;
import kielce.tu.weaii.telelearn.requests.courses.PostRequest;
import kielce.tu.weaii.telelearn.services.ports.PostService;
import kielce.tu.weaii.telelearn.views.courses.CommentView;
import kielce.tu.weaii.telelearn.views.courses.PostView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

 private  PostService postService;


@DeleteMapping(path = { "/{id}" })
public ResponseEntity<Object> deletePost(Long id){
    postService.removePost(id);
    return ResponseEntity.noContent().build();
}


@PutMapping(path = { "{id}" })
public ResponseEntity<Object> editPost(Long id,PostRequest request,List<MultipartFile> files){
    try {
        postService.updatePost(id, request, files);
    } catch (IOException e) {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity.noContent().build();
}


@GetMapping("{id}")
public ResponseEntity<PostView> getPost(Long id){
    return new ResponseEntity<>(PostView.from(postService.getById(id)), HttpStatus.OK);
}


@PostMapping()
public ResponseEntity<Object> addPost(PostRequest request,List<MultipartFile> files){
    try {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postService.addPost(request, files).getId()).toUri();
        return ResponseEntity.created(location).build();
    } catch (IOException e) {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@GetMapping(path = "/{id}/comment")
public ResponseEntity<List<CommentView>> getPostComments(Long id){
    return new ResponseEntity<>(postService.getComments(id).stream().map(CommentView::from).collect(Collectors.toList()), HttpStatus.OK);
}


@PostMapping(path = "/{id}/comment")
public ResponseEntity<Object> addComment(Long id,PostCommentRequest request){
    postService.addComment(id, request);
    return ResponseEntity.noContent().build();
}


}