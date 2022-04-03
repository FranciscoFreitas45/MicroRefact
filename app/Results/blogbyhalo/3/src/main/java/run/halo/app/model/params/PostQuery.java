package run.halo.app.model.params;
 import lombok.Data;
import run.halo.app.model.enums.PostStatus;
@Data
public class PostQuery {

 private  String keyword;

 private  PostStatus status;

 private  Integer categoryId;


}