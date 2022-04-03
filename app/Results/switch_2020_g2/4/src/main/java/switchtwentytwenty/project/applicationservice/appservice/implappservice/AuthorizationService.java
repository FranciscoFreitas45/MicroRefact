package switchtwentytwenty.project.applicationservice.appservice.implappservice;
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
import switchtwentytwenty.project.DTO.Person;
@Service
public class AuthorizationService implements IAuthorizationService{

 private  String ERROR_ROLE_NOT_FOUND;

@Autowired
 private UserRepository userRepository;

@Autowired
 private PasswordEncoder encoder;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private IPersonRepository personRepository;


public boolean accessAccountAuthorization(String username,String accountId){
    Email personID = this.getPersonIDOfUser(username);
    Person person = personRepository.findByID(personID);
    AccountID accountID = new AccountID(UUID.fromString(accountId));
    return person.isMyAccount(accountID);
}


public Email getPersonIDOfUser(String username){
    Optional<UserJPA> user = userRepository.findByUsername(username);
    if (!user.isPresent()) {
        throw new UserEmailNotFoundException("Username not found in database");
    }
    return new Email(user.get().getEmail());
}


@Transactional(rollbackFor = Exception.class)
public void registerUser(SignupDTO signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        throw new BusinessErrorMessage("Error: Username is already taken!", HttpStatus.BAD_REQUEST.value());
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        throw new BusinessErrorMessage("Error: Email is already in use!", HttpStatus.BAD_REQUEST.value());
    }
    UserJPA user = new UserJPA(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getFamilyID());
    Set<String> strRoles = signUpRequest.getRole();
    Set<RoleJPA> roles = new HashSet<>();
    if (strRoles == null) {
        RoleJPA userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
        roles.add(userRole);
    } else {
        strRoles.forEach(role -> {
            switch(role) {
                case "ROLE_ADMIN":
                    RoleJPA adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
                    roles.add(adminRole);
                    break;
                case "ROLE_SYSTEM_MANAGER":
                    RoleJPA systemRole = roleRepository.findByName(ERole.ROLE_SYSTEM_MANAGER).orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
                    roles.add(systemRole);
                    break;
                default:
                    RoleJPA userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
                    roles.add(userRole);
            }
        });
    }
    user.setRoles(roles);
    userRepository.save(user);
}


public String getRole(String tokenInfo){
    String[] authorities = tokenInfo.split("Authorities=");
    String role = authorities[1].substring(1);
    role = role.substring(0, role.length() - 1);
    return role.substring(0, role.length() - 1);
}


public boolean accessFamilyCashAccountAuthorization(String role){
    return role.equals(ERole.ROLE_ADMIN.toString());
}


public String getFamilyID(String username){
    Optional<UserJPA> user = userRepository.findByUsername(username);
    if (!user.isPresent()) {
        throw new UserEmailNotFoundException("Username not found in database");
    }
    return user.get().getFamilyID();
}


}