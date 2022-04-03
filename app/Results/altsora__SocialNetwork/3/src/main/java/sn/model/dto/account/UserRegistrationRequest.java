package sn.model.dto.account;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.reflect.Field;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

 private  String email;

 private  String passwd1;

 private  String passwd2;

 private  String firstName;

 private  String lastName;

 private  String code;


}