package switchtwentytwenty.project.DTO;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import switchtwentytwenty.project.Interface.IPersonRepository;
public class AuthorizationService implements IAuthorizationService{

 private  String ERROR_ROLE_NOT_FOUND;

 private UserRepository userRepository;

 private PasswordEncoder encoder;

 private RoleRepository roleRepository;

 private IPersonRepository personRepository;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public Email getPersonIDOfUser(String username){
    Optional<UserJPA> user = userRepository.findByUsername(username);
    if (!user.isPresent()) {
        throw new UserEmailNotFoundException("Username not found in database");
    }
    return new Email(user.get().getEmail());
}


public String getRole(String tokenInfo){
    String[] authorities = tokenInfo.split("Authorities=");
    String role = authorities[1].substring(1);
    role = role.substring(0, role.length() - 1);
    return role.substring(0, role.length() - 1);
}


public String getFamilyID(String username){
    Optional<UserJPA> user = userRepository.findByUsername(username);
    if (!user.isPresent()) {
        throw new UserEmailNotFoundException("Username not found in database");
    }
    return user.get().getFamilyID();
}


public boolean accessFamilyCashAccountAuthorization(String role){
    return role.equals(ERole.ROLE_ADMIN.toString());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/accessFamilyCashAccountAuthorization"))

.queryParam("role",role)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}