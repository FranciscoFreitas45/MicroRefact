package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
public class LoginRequest {

 private  String userNameOrEmail;

 private  char[] password;

@JsonCreator
public LoginRequest(@JsonProperty(value = "userNameOrEmail", required = true) String userName, @JsonProperty(value = "password", required = true) char[] password) {
    this.userNameOrEmail = userName;
    this.password = password;
}
}