package main.model.request.passwords;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest extends PasswordRequest{

 private  String e_mail;


}