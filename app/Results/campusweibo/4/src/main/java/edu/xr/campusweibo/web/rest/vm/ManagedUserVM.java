package edu.xr.campusweibo.web.rest.vm;
 import edu.xr.campusweibo.service.dto.UserDTO;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Set;
public class ManagedUserVM extends UserDTO{

 public  int PASSWORD_MIN_LENGTH;

 public  int PASSWORD_MAX_LENGTH;

@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
 private  String password;


public String getPassword(){
    return password;
}


@Override
public String toString(){
    return "ManagedUserVM{" + "} " + super.toString();
}


}