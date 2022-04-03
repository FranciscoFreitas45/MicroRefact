package run.halo.app.model.dto;
 import lombok.Data;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.Link;
@Data
public class LinkDTO implements OutputConverter<LinkDTO, Link>{

 private  Integer id;

 private  String name;

 private  String url;

 private  String logo;

 private  String description;

 private  String team;

 private  Integer priority;


}