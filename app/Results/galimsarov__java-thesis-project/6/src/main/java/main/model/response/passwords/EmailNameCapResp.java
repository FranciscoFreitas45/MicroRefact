package main.model.response.passwords;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class EmailNameCapResp extends PasswordResponse{

 private  String email;

 private  String name;

 private  String captcha;


}