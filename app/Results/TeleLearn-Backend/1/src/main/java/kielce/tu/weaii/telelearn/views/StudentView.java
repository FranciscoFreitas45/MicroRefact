package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.Student;
import kielce.tu.weaii.telelearn.models.UserRole;
import lombok.Value;
@Value
public class StudentView {

 private Long id;

 private String username;

 private String email;

 private String name;

 private String surname;

 private UserRole userRole;

 private boolean enabled;

 private String unit;

 private TimeVew dailyLearningTime;


public StudentView from(Student model,boolean loginPermitted){
    return new StudentView(model.getId(), (loginPermitted) ? model.getUsername() : null, model.getEmail(), model.getName(), model.getSurname(), model.getUserRole(), model.isEnabled(), model.getUnit(), TimeVew.form(model.getDailyLearningTime()));
}


}