package main.model.response.ids;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Data
public class PostResponse extends IdResponse{

 private  long timestamp;

 private  boolean active;

 private  IdNameResp user;

 private  String title;

 private  String text;

 private  int likeCount;

 private  int dislikeCount;

 private  int viewCount;

 private  List<CommentResponse> comments;

 private  Set<String> tags;


}