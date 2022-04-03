package run.halo.app.service;
 import org.springframework.lang.NonNull;
import run.halo.app.model.dto.EnvironmentDTO;
import run.halo.app.model.dto.LoginPreCheckDTO;
import run.halo.app.model.entity.User;
import run.halo.app.model.params.LoginParam;
import run.halo.app.model.params.ResetPasswordParam;
import run.halo.app.security.token.AuthToken;
public interface AdminService {

 private int ACCESS_TOKEN_EXPIRED_SECONDS;

 private int REFRESH_TOKEN_EXPIRED_DAYS;

 private String LOG_PATH;


@NonNull
public AuthToken authCodeCheck(LoginParam loginParam)
;

@NonNull
public EnvironmentDTO getEnvironments()
;

public String getLogFiles(Long lines)
;

@NonNull
public User authenticate(LoginParam loginParam)
;

public void sendResetPasswordCode(ResetPasswordParam param)
;

public void clearToken()
;

public LoginPreCheckDTO getUserEnv(String username)
;

public void resetPasswordByCode(ResetPasswordParam param)
;

@NonNull
public AuthToken refreshToken(String refreshToken)
;

}