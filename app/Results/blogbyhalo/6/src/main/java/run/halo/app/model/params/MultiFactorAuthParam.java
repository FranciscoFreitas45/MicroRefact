package run.halo.app.model.params;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import run.halo.app.model.enums.MFAType;
@Data
public class MultiFactorAuthParam {

 private  MFAType mfaType;

 private  String mfaKey;

@NotBlank(message = "MFA Code不能为空")
@Size(min = 6, max = 6, message = "MFA Code应为 {max} 位")
 private  String authcode;


}