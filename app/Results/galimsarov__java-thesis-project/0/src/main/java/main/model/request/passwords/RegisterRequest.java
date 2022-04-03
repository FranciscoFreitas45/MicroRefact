package main.model.request.passwords;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterRequest extends LoginRequest{

 private  String name;

 private  String captcha;

 private  String captcha_secret;


}