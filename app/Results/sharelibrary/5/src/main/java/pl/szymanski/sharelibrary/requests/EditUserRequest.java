package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class EditUserRequest {

 private  String name;

 private  String surname;

 private  CoordinatesRequest coordinates;

@JsonCreator
public EditUserRequest(@JsonProperty("name") String name, @JsonProperty("surname") String surname, @JsonProperty("coordinates") CoordinatesRequest coordinates) {
    this.name = name;
    this.surname = surname;
    this.coordinates = coordinates;
}
}