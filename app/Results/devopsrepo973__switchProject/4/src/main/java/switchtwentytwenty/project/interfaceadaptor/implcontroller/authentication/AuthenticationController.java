package switchtwentytwenty.project.interfaceadaptor.implcontroller.authentication;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.autentication.JwtResponseDTO;
import switchtwentytwenty.project.autentication.JwtUtils;
import switchtwentytwenty.project.autentication.LoginRequestDTO;
import switchtwentytwenty.project.autentication.UserDetailsImpl;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

@Autowired
 private AuthenticationManager authenticationManager;

@Autowired
 private JwtUtils jwtUtils;


@PostMapping("/login")
public ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest){
    Authentication authentication = authenticationManager.authenticate(// encripta para comparar com o que foi guardar
    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
    return ResponseEntity.ok(new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getFamilyID(), roles));
}


}