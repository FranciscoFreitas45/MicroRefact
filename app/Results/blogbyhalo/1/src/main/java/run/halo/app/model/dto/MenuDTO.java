package run.halo.app.model.dto;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.Menu;
@Data
@EqualsAndHashCode
@ToString
public class MenuDTO implements OutputConverter<MenuDTO, Menu>{

 private  Integer id;

 private  String name;

 private  String url;

 private  Integer priority;

 private  String target;

 private  String icon;

 private  Integer parentId;

 private  String team;


}