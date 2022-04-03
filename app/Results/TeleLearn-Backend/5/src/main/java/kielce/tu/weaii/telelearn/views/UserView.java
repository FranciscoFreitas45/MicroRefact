package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import lombok.Value;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType.IDENTITY;
@Value
public class UserView {

@Id
@GeneratedValue(strategy = IDENTITY)
 private Long id;

 private String username;

 private String email;

 private String name;

 private String surname;

 private UserRole userRole;

 private boolean enabled;


public UserView from(User model,boolean loginPermitted){
    return new UserView(model.getId(), (loginPermitted) ? model.getUsername() : null, model.getEmail(), model.getName(), model.getSurname(), model.getUserRole(), model.isEnabled());
}


}