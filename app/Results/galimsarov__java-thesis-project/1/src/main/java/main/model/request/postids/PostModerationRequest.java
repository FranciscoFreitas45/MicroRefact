package main.model.request.postids;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class PostModerationRequest extends PostIdRequest{

 private  String decision;


}