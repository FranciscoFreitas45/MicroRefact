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

public ManagedUserVM() {
// Empty constructor needed for Jackson.
}public ManagedUserVM(Long id, String login, String password, String firstName, String lastName, String email, boolean activated, String imageUrl, String langKey, String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate, Set<String> authorities) {
    super(id, login, firstName, lastName, email, activated, imageUrl, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities);
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