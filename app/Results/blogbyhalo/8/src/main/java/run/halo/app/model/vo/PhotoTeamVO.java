package run.halo.app.model.vo;
 import java.util.List;
import lombok.Data;
import lombok.ToString;
import run.halo.app.model.dto.PhotoDTO;
@Data
@ToString
public class PhotoTeamVO {

 private  String team;

 private  List<PhotoDTO> photos;


}