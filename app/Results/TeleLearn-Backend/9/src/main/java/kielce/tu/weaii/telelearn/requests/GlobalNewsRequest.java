package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Validated
@Getter
@EqualsAndHashCode
public class GlobalNewsRequest {

@NotBlank(message = "Artykuł musi mieć tytuł")
 private  String title;

@NotNull(message = "Artykuł musi mieć autora")
 private  Long authorId;

 private  String brief;

 private  String htmlContent;

@NotNull(message = "Nie podano daty publikacji")
 private  LocalDateTime publicationDate;

public GlobalNewsRequest(@JsonProperty(value = "title", required = true) String title, @JsonProperty(value = "authorId", required = true) Long authorId, @JsonProperty(value = "brief") String brief, @JsonProperty(value = "htmlContent") String htmlContent, @JsonProperty(value = "publicationDate", required = true) LocalDateTime publicationDate) {
    this.title = title;
    this.authorId = authorId;
    this.brief = brief;
    this.htmlContent = htmlContent;
    this.publicationDate = publicationDate;
}
}