package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Validated
@Getter
@EqualsAndHashCode
public class TeacherUpdateRequest {

@NotBlank(message = "Email nie może być pusty")
@Email(message = "Podaj poprawny email")
 private  String email;

@NotBlank(message = "Imię nie może być puste")
 private  String name;

@NotBlank(message = "Nazwisko nie może być puste")
 private  String surname;

 private  String unit;

 private  String title;

public TeacherUpdateRequest(@JsonProperty(value = "email") String email, @JsonProperty(value = "name") String name, @JsonProperty(value = "surname") String surname, @JsonProperty(value = "unit", defaultValue = "null") String unit, @JsonProperty(value = "title", defaultValue = "null") String title) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.unit = unit;
    this.title = title;
}
}