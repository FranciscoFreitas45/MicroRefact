package org.vaadin.paul.spring.app.security;
 import com.vaadin.flow.server.ServletHelper.RequestType;
import com.vaadin.flow.shared.ApplicationConstants;
import org.hibernate.mapping.Collection;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class SecurityUtils {

private SecurityUtils() {
// Util methods only
}
public UserDetails getAuthenticatedUser(){
    SecurityContext context = SecurityContextHolder.getContext();
    Object principal = context.getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
        return userDetails;
    }
    // Anonymous or no authentication.
    return null;
}


public boolean isFrameworkInternalRequest(HttpServletRequest request){
    final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
    return parameterValue != null && Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
}


public boolean isUserLoggedIn(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
}


public boolean isAccessGranted(Class<?> securedClass){
    // Allow if no roles are required.
    Secured secured = AnnotationUtils.findAnnotation(securedClass, Secured.class);
    if (secured == null) {
        return true;
    }
    // lookup needed role in user roles
    final List<String> allowedRoles = Arrays.asList(secured.value());
    final Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
    return userAuthentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(allowedRoles::contains);
}


}