package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class AuthorRequest {

 private  String name;

 private  String surname;

@JsonCreator
public AuthorRequest(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "surname", required = true) String surname) {
    this.name = name;
    this.surname = surname;
}
}