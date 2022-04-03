package kielce.tu.weaii.telelearn.views.courses;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
@Value
public class CourseSignUpResponse {

@JsonProperty("isAccepted")
 private boolean accepted;


}