package kielce.tu.weaii.telelearn.requests.courses;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Valid
@Getter
@EqualsAndHashCode
public class TaskRepeatPatchRequest {

@NotNull(message = "Należy podać id ucznia.")
 private  Long studentId;

@NotNull(message = "Należy określić czy zadanie jest do powtórki.")
 private  Boolean toRepeat;

public TaskRepeatPatchRequest(@JsonProperty(value = "studentId", required = true) Long studentId, @JsonProperty(value = "isToRepeat", required = true) Boolean toRepeat) {
    this.studentId = studentId;
    this.toRepeat = toRepeat;
}
}