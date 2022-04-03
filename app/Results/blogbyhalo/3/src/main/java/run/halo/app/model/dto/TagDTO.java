package run.halo.app.model.dto;
 import java.util.Date;
import lombok.Data;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.Tag;
@Data
public class TagDTO implements OutputConverter<TagDTO, Tag>{

 private  Integer id;

 private  String name;

 private  String slug;

 private  String thumbnail;

 private  Date createTime;

 private  String fullPath;


}