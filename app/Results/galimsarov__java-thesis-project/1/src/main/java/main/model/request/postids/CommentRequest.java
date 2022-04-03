package main.model.request.postids;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentRequest extends PostIdRequest{

 private  int parent_id;

 private  String text;


}