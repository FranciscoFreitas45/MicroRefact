package kielce.tu.weaii.telelearn.requests.courses;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Valid
@Getter
@EqualsAndHashCode
public class CourseStudentRequest {

@NotNull(message = "Należy podać id użytkownika")
 private  Long studentId;

public CourseStudentRequest(@JsonProperty(value = "userId", required = true) Long studentId) {
    this.studentId = studentId;
}
}