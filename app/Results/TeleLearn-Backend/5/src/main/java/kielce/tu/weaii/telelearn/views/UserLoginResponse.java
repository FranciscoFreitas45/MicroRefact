package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import lombok.Value;
@Value
public class UserLoginResponse {

 private Long id;

 private String login;

 private String name;

 private String surname;

 private UserRole userRole;


public UserLoginResponse of(User user){
    return new UserLoginResponse(user.getId(), user.getUsername(), user.getName(), user.getSurname(), user.getUserRole());
}


}