package run.halo.app.security.authentication;
 import org.springframework.lang.NonNull;
import run.halo.app.security.support.UserDetail;
public interface Authentication {


@NonNull
public UserDetail getDetail()
;

}