package main.service;
 import main.model.request.others.PostRequest;
import main.model.request.postids.PostIdRequest;
import main.model.response.others.ThePosts;
import main.model.response.results.ResultResponse;
public interface PostService {


public Object editPost(int id,PostRequest request)
;

public ThePosts getPostsForModeration(int offset,int limit,String status)
;

public Object getPost(int id)
;

public ResultResponse like(PostIdRequest request)
;

public ResultResponse dislike(PostIdRequest request)
;

public ThePosts getListOfPostResponse(int offset,int limit,String mode)
;

public ThePosts getPostsByTag(int offset,int limit,String tag)
;

public Object addPost(PostRequest request)
;

public ThePosts getPostsByDate(int offset,int limit,String date)
;

public ThePosts searchForPostResponse(int offset,int limit,String query)
;

public ThePosts getMyPosts(int offset,int limit,String status)
;

}