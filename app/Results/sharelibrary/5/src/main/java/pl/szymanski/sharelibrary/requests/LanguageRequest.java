package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class LanguageRequest {

 private  Integer id;

 private  String name;

@JsonCreator
public LanguageRequest(@JsonProperty(value = "id") Integer id, @JsonProperty(value = "name") String name) {
    this.id = id;
    this.name = name;
}
}