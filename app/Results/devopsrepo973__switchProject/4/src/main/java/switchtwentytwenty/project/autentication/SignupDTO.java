package switchtwentytwenty.project.autentication;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;
@AllArgsConstructor
public class SignupDTO {

@Getter
 private String username;

@Getter
 private String email;

@Getter
 private String password;

@Getter
 private String familyID;

@Getter
 private Set<String> role;


}