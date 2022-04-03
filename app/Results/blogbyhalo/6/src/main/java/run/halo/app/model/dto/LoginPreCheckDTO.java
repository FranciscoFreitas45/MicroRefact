package run.halo.app.model.dto;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
public class LoginPreCheckDTO {

 private  boolean needMFACode;


}