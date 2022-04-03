package run.halo.app.model.params;
 import lombok.Data;
import run.halo.app.model.enums.OptionType;
@Data
public class OptionQuery {

 private  String keyword;

 private  OptionType type;


}