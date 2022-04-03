package run.halo.app.security.context;
 import org.springframework.lang.Nullable;
import run.halo.app.security.authentication.Authentication;
public interface SecurityContext {


@Nullable
public Authentication getAuthentication()
;

public boolean isAuthenticated(){
    return getAuthentication() != null;
}
;

public void setAuthentication(Authentication authentication)
;

}