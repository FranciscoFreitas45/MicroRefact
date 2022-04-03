package es.us.isa.ideas.app.social;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountService;
@Component
public class SignInUtils {

@Autowired
 private UserAccountService userAccountService;

@Autowired
 private AuthenticationManager authenticationManager;


public void signin(String userId,String providerId){
    UsernamePasswordAuthenticationToken token = null;
    // En caso buscamos una cuenta de usuariocon el mismo userId:
    UserAccount userAccount = userAccountService.findByUsername(userId);
    if (userAccount != null) {
        token = new UsernamePasswordAuthenticationToken(userAccount, null, userAccount.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}


}