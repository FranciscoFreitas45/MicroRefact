package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.security.JwtAuthenticationResponse;
@Data
@AllArgsConstructor
public class UserLoginResponse {

 private  Long id;

 private  String userName;

 private  JwtAuthenticationResponse response;


public UserLoginResponse of(User user,JwtAuthenticationResponse jwtAuthenticationResponse){
    return new UserLoginResponse(user.getId(), user.getUsername(), jwtAuthenticationResponse);
}


}