package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.validation.constraints.NotNull;
@Getter
@EqualsAndHashCode
public class UserPasswordPatchRequest {

 private  char[] oldPassword;

@NotNull(message = "Nowe nie może być puste")
 private  char[] newPassword;

public UserPasswordPatchRequest(@JsonProperty(value = "oldPassword") char[] oldPassword, @JsonProperty(value = "newPassword") char[] newPassword) {
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
}
}