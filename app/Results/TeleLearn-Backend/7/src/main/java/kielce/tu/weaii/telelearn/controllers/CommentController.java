package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.services.ports.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

 private  PostService postService;


@DeleteMapping(path = "{id}")
public ResponseEntity<Object> delete(Long id){
    postService.deleteComment(id);
    return ResponseEntity.noContent().build();
}


}