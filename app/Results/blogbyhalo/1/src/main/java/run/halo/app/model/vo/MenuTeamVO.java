package run.halo.app.model.vo;
 import java.util.List;
import lombok.Data;
import lombok.ToString;
import run.halo.app.model.dto.MenuDTO;
@Data
@ToString
public class MenuTeamVO {

 private  String team;

 private  List<MenuDTO> menus;


}