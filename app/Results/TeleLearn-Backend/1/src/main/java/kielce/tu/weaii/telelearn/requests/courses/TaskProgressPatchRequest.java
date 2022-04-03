package kielce.tu.weaii.telelearn.requests.courses;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Valid
@Getter
@EqualsAndHashCode
public class TaskProgressPatchRequest {

@NotNull(message = "Należy podać id ucznia.")
 private  Long studentId;

@NotNull(message = "Należy podać procent ukończenia.")
@Min(value = 0, message = "Postęp nie może być mniejszy niż 0.")
@Max(value = 100, message = "Postęp nie może być większy niż 100.")
 private  Integer progress;

public TaskProgressPatchRequest(@JsonProperty(value = "studentId", required = true) Long studentId, @JsonProperty(value = "progress", required = true) Integer progress) {
    this.studentId = studentId;
    this.progress = progress;
}
}