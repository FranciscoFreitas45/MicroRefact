package sn.api.requests;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class PostCommentCreateRequest {

 private  long parentId;

 private  String commentText;


}