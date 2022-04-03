package run.halo.app.model.params;
 import lombok.Data;
import run.halo.app.model.enums.JournalType;
@Data
public class JournalQuery {

 private  String keyword;

 private  JournalType type;


}