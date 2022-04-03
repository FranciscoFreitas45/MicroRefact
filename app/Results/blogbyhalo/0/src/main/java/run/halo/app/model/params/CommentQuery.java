package run.halo.app.model.params;
 import lombok.Data;
import run.halo.app.model.enums.CommentStatus;
@Data
public class CommentQuery {

 private  String keyword;

 private  CommentStatus status;


}