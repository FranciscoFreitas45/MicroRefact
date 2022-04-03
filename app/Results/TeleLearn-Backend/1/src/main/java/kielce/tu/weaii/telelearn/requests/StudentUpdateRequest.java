package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
@Validated
@Getter
@EqualsAndHashCode
public class StudentUpdateRequest {

@NotBlank(message = "Email nie może być pusty")
@Email(message = "Podaj poprawny email")
 private  String email;

@NotBlank(message = "Imię nie może być puste")
 private  String name;

@NotBlank(message = "Nazwisko nie może być puste")
 private  String surname;

 private  String unit;

@JsonIgnore
 private  Duration dailyLearningTime;

public StudentUpdateRequest(@JsonProperty(value = "email") String email, @JsonProperty(value = "name") String name, @JsonProperty(value = "surname") String surname, @JsonProperty(value = "unit") String unit, @JsonProperty(value = "hours", required = true) long hours, @JsonProperty(value = "minutes", required = true) long minutes) {
    if (hours < 0 || hours > 23) {
        throw new IllegalArgumentException("Nieprawidłowa liczba godzin");
    }
    if (minutes < 0 || minutes >= 60) {
        throw new IllegalArgumentException("Nieprawidłowa liczba minut");
    }
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.unit = unit;
    this.dailyLearningTime = Duration.ofHours(hours).plus(Duration.ofMinutes(minutes));
}
@JsonProperty
public long getHours(){
    return dailyLearningTime.toHours();
}


@JsonProperty
public long getMinutes(){
    return dailyLearningTime.minusHours(getHours()).toMinutes();
}


}