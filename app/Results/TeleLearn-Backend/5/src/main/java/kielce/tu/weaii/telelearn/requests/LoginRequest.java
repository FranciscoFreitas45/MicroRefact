package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
@Validated
@Getter
@EqualsAndHashCode
public class LoginRequest {

@NotBlank(message = "Należy podać login")
 private  String userName;

 private  char[] password;

@JsonCreator
public LoginRequest(@JsonProperty(value = "login", required = true) String userName, @JsonProperty(value = "password", required = true) char[] password) {
    this.userName = userName;
    this.password = password;
}
}