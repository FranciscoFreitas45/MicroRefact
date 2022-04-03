package app.qienuren.security;
 import app.qienuren.controller.PersoonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

@Autowired
 private PersoonRepository persoonRepository;

 protected  Log logger;

 private  RedirectStrategy redirectStrategy;

public MyAuthenticationSuccessHandler() {
    super();
}
public String determineTargetUrl(Authentication authentication){
    System.out.println(">>>>>>>>>>>>>>>>>>>" + authentication.getName());
    long id = persoonRepository.findByUserName(authentication.getName()).get().getId();
    Map<String, String> roleTargetUrlMap = new HashMap<>();
    roleTargetUrlMap.put("ROLE_TRAINEE", "/trainee?id=" + id);
    // roleTargetUrlMap.put("ROLE_TRAINEE", "/profielpagina?id=" + id); //staat niet goed, nu na inloggen na profielpagina
    roleTargetUrlMap.put("ROLE_ADMIN", "/admin");
    roleTargetUrlMap.put("ROLE_INTERNEMEDEWERKER", "/medewerker?id=" + id);
    roleTargetUrlMap.put("ROLE_KCP", "/opdrachtgever?id=" + id);
    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
        String authorityName = grantedAuthority.getAuthority();
        if (roleTargetUrlMap.containsKey(authorityName)) {
            return roleTargetUrlMap.get(authorityName);
        }
    }
    throw new IllegalStateException();
}


@Override
public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException{
    handle(request, response, authentication);
    clearAuthenticationAttributes(request);
}


public void handle(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException{
    final String targetUrl = determineTargetUrl(authentication);
    if (response.isCommitted()) {
        logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
        return;
    }
    redirectStrategy.sendRedirect(request, response, targetUrl);
}


public void clearAuthenticationAttributes(HttpServletRequest request){
    final HttpSession session = request.getSession(false);
    if (session == null) {
        return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
}


}