package es.gva.dgti.gvgeoportal.util;
 import org.springframework.security.core.context.SecurityContextHolder;
import es.gva.dgti.gvgeoportal.security.SafeUser;
public class SecurityUtils {


public SafeUser getCurrentAuthentication(){
    return (SafeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
}


}