package run.halo.app.model.dto;
 import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.Category;
@Data
@ToString
@EqualsAndHashCode
public class CategoryDTO implements OutputConverter<CategoryDTO, Category>{

 private  Integer id;

 private  String name;

 private  String slug;

 private  String description;

 private  String thumbnail;

 private  Integer parentId;

 private  String password;

 private  Date createTime;

 private  String fullPath;


}