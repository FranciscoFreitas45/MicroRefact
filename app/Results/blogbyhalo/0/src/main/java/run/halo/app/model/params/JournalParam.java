package run.halo.app.model.params;
 import javax.validation.constraints.NotBlank;
import lombok.Data;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Journal;
import run.halo.app.model.enums.JournalType;
@Data
public class JournalParam implements InputConverter<Journal>{

@NotBlank(message = "内容不能为空")
 private  String sourceContent;

 private  JournalType type;


}