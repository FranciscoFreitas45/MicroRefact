import cn.com.cnc.fcc.service.dto.UserDTO;
import javax.validation.constraints.Size;
public class ManagedUserVM extends UserDTO {

 public  int PASSWORD_MIN_LENGTH;

 public  int PASSWORD_MAX_LENGTH;

@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
 private  String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


@Override
public String toString(){
    return "ManagedUserVM{" + "} " + super.toString();
}


}