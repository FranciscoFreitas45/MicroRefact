package main.model.request.passwords;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class ChangePasswordRequest extends PasswordRequest{

 private  String code;

 private  String captcha;

 private  String captcha_secret;


}