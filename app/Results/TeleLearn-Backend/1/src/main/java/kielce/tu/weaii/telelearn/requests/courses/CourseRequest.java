package kielce.tu.weaii.telelearn.requests.courses;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Validated
@Getter
public class CourseRequest {

@NotNull(message = "Nie podano właściciela kursu")
 private  Long ownerId;

@NotBlank(message = "Nazwa nie może być pusta.")
 private  String name;

 private  String welcomePageHtmlContent;

@NotNull(message = "Musi być określone czy kurs jest publiczny.")
 private  boolean publicCourse;

@NotNull(message = "Musi być określone czy użytkownicy są akceptowany automatycznie.")
 private  boolean autoAccept;

@NotNull(message = "Musi być określone czy uczniowe mogą dodawać posty.")
 private  boolean studentsAllowedToPost;

public CourseRequest(@JsonProperty(value = "ownerId", required = true) Long ownerId, @JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "welcomePageHtmlContent") String welcomePageHtmlContent, @JsonProperty(value = "isPublicCourse", required = true) boolean publicCourse, @JsonProperty(value = "isAutoAccept", required = true) boolean autoAccept, @JsonProperty(value = "areStudentsAllowedToPost", required = true) boolean studentsAllowedToPost) {
    this.ownerId = ownerId;
    this.name = name;
    this.welcomePageHtmlContent = welcomePageHtmlContent;
    this.publicCourse = publicCourse;
    this.autoAccept = autoAccept;
    this.studentsAllowedToPost = studentsAllowedToPost;
}
}