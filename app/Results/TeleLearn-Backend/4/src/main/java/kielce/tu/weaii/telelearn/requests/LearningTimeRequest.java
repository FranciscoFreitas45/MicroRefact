package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Valid
@Getter
@EqualsAndHashCode
public class LearningTimeRequest {

@NotNull(message = "Należy określić ucznia.")
 private  Long studentId;

@NotNull(message = "Należy określić datę.")
 private  LocalDate date;

@NotNull(message = "Należy określić czas.")
@Valid
 private  TimeSpanRequest time;

public LearningTimeRequest(@JsonProperty(value = "studentId", required = true) Long studentId, @JsonProperty(value = "date", required = true) String date, @JsonProperty(value = "time", required = true) TimeSpanRequest time) {
    this.studentId = studentId;
    this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    this.time = time;
}
}