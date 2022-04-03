package run.halo.app.model.vo;
 import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.dto.post.BasePostSimpleDTO;
@EqualsAndHashCode(callSuper = true)
@Data
public class PostListVO extends BasePostSimpleDTO{

 private  Long commentCount;

 private  List<TagDTO> tags;

 private  List<CategoryDTO> categories;

 private  Map<String,Object> metas;


}