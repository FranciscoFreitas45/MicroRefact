package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.Teacher;
import kielce.tu.weaii.telelearn.models.UserRole;
import lombok.Value;
@Value
public class TeacherView {

 private Long id;

 private String username;

 private String email;

 private String name;

 private String surname;

 private UserRole userRole;

 private boolean enabled;

 private String unit;

 private String title;


public TeacherView from(Teacher model,boolean loginPermitted){
    return new TeacherView(model.getId(), (loginPermitted) ? model.getUsername() : null, model.getEmail(), model.getName(), model.getSurname(), model.getUserRole(), model.isEnabled(), model.getUnit(), model.getTitle());
}


}