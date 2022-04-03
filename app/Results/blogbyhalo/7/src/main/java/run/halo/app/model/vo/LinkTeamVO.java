package run.halo.app.model.vo;
 import java.util.List;
import lombok.Data;
import lombok.ToString;
import run.halo.app.model.dto.LinkDTO;
@Data
@ToString
public class LinkTeamVO {

 private  String team;

 private  List<LinkDTO> links;


}