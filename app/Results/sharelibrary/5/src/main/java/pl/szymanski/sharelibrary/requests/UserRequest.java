package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserRequest {

 private  String email;

 private  String username;

 private  char[] password;

 private  String name;

 private  String surname;

 private  CoordinatesRequest coordinates;

@JsonCreator
public UserRequest(@JsonProperty(value = "email", required = true) String email, @JsonProperty(value = "username", required = true) String username, @JsonProperty(value = "password", required = true) char[] password, @JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "surname", required = true) String surname, @JsonProperty(value = "coordinates", required = true) CoordinatesRequest coordinates) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.coordinates = coordinates;
}
}