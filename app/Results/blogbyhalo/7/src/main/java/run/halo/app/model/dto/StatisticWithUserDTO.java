package run.halo.app.model.dto;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import run.halo.app.model.dto.base.OutputConverter;
@Data
@EqualsAndHashCode(callSuper = true)
public class StatisticWithUserDTO extends StatisticDTOimplements OutputConverter<StatisticWithUserDTO, StatisticDTO>{

 private  UserDTO user;


}