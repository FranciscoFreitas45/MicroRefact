package main.model.response.ids;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResponse extends IdResponse{

 private  int parentId;

 private  long timestamp;

 private  String text;

 private  IdNamePhotoResp user;


}