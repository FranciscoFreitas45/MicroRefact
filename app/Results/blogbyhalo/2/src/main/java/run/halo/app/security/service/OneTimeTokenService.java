package run.halo.app.security.service;
 import java.util.Optional;
import org.springframework.lang.NonNull;
public interface OneTimeTokenService {


@NonNull
public Optional<String> get(String oneTimeToken)
;

@NonNull
public String create(String uri)
;

public void revoke(String oneTimeToken)
;

}