package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymanski.sharelibrary.entity.User;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseUserResponse {

 private  Long id;

 private  String email;

 private  String username;

 private  String name;

 private  String surname;

 private  CoordinatesResponse coordinates;


public BaseUserResponse of(User user){
    return new BaseUserResponse(user.getId(), user.getEmail(), user.getUsername(), user.getName(), user.getSurname(), CoordinatesResponse.of(user.getCoordinates()));
}


}