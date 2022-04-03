package run.halo.app.model.dto.post;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BasePostSimpleDTO extends BasePostMinimalDTO{

 private  String summary;

 private  String thumbnail;

 private  Long visits;

 private  Boolean disallowComment;

 private  String password;

 private  String template;

 private  Integer topPriority;

 private  Long likes;

 private  Long wordCount;


public boolean isTopped(){
    return this.topPriority != null && this.topPriority > 0;
}


}