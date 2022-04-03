package kielce.tu.weaii.telelearn.requests.courses;
 import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@Valid
public class TaskRequest {

@NotBlank(message = "Nie podano nazwy zadania.")
 private  String name;

 private  String description;

@Min(value = 0, message = "Wartość godzin nie może być ujemna.")
 private  int learningTimeHours;

@Min(value = 0, message = "Wartość minut nie może być ujemna.")
@Max(value = 60, message = "Wartość minut nie może być większa niż 60.")
 private  int learningTimeMinutes;

@NotNull(message = "Nie podano daty zakończenia zadania.")
@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  LocalDate dueDate;

@NotNull(message = "Nie podano ścieżki.")
 private  Long courseId;

 private  List<Long> attachmentIdsToDelete;

 private  List<Long> previousTaskIds;


}