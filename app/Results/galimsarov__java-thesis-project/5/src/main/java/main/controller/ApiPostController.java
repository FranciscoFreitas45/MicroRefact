package main.controller;
 import main.model.request.others.PostRequest;
import main.model.request.postids.PostIdRequest;
import main.model.response.others.ThePosts;
import main.model.response.results.ResultResponse;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
@RestController
@RequestMapping("/api/post")
public class ApiPostController {

@Autowired
 private PostService postService;


@GetMapping("/my")
public ThePosts listOfMyPosts(int offset,int limit,String status){
    return postService.getMyPosts(offset, limit, status);
}


@PutMapping("/{id}")
public Object editPost(int id,PostRequest request){
    return postService.editPost(id, request);
}


@GetMapping("/{id}")
public Object getPost(int id){
    return postService.getPost(id);
}


@PostMapping("/like")
public ResultResponse like(PostIdRequest request){
    return postService.like(request);
}


@PostMapping("/dislike")
public ResultResponse dislike(PostIdRequest request){
    return postService.dislike(request);
}


@GetMapping
public ThePosts listOfPosts(int offset,int limit,String mode){
    return postService.getListOfPostResponse(offset, limit, mode);
}


@PostMapping
public Object addPost(PostRequest request){
    return postService.addPost(request);
}


@GetMapping("/search")
public ThePosts searchForPosts(int offset,int limit,String query){
    return postService.searchForPostResponse(offset, limit, query);
}


@GetMapping("/byDate")
public ThePosts postsByDate(int offset,int limit,String date){
    return postService.getPostsByDate(offset, limit, date);
}


@GetMapping("/byTag")
public ThePosts postsByTag(int offset,int limit,String tag){
    return postService.getPostsByTag(offset, limit, tag);
}


@GetMapping("/moderation")
public ThePosts listOfPostsForModeration(int offset,int limit,String status){
    return postService.getPostsForModeration(offset, limit, status);
}


}