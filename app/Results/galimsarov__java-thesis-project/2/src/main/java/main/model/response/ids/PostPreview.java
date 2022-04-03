package main.model.response.ids;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class PostPreview extends IdResponse{

 private  long timestamp;

 private  IdNameResp user;

 private  String title;

 private  String announce;

 private  int likeCount;

 private  int dislikeCount;

 private  int commentCount;

 private  int viewCount;


}