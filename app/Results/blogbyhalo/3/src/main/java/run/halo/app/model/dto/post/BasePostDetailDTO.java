package run.halo.app.model.dto.post;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BasePostDetailDTO extends BasePostSimpleDTO{

 private  String originalContent;

 private  String formatContent;

 private  Long commentCount;


}