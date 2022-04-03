package main.DTO;
 import lombok;
import main.model.helper.PostStatus;
import org.hibernate.annotations.Type;
import javax.persistence;
import java.util;
public class Post {

 private  int id;

 private  boolean isActive;

 private  PostStatus moderationStatus;

 private  int moderatorId;

 private  Date time;

 private  String title;

 private  String text;

 private  int viewCount;

 private  User user;

 private  List<PostVote> postVoteList;

 private  List<PostComment> postCommentList;

 private  Set<Tag> tagSet;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public void addPostComment(PostComment postComment){
    postCommentList.add(postComment);
    postComment.setPost(this);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/addPostComment"))

.queryParam("postComment",postComment)
;
restTemplate.put(builder.toUriString(),null);
}


}