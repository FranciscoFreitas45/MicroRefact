package main.model.response.passwords;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class CodePasCapResp extends PasswordResponse{

 private  String code;

 private  String captcha;


}