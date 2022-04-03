package main.service;
 import main.model.request.others.EmailRequest;
import main.model.request.passwords.ChangePasswordRequest;
import main.model.request.passwords.LoginRequest;
import main.model.request.passwords.RegisterRequest;
import main.model.response.others.CaptchaResponse;
import main.model.response.results.ResultResponse;
import java.io.IOException;
public interface AuthService {


public ResultResponse logout()
;

public ResultResponse restore(EmailRequest emailRequest)
;

public CaptchaResponse captcha()
;

public ResultResponse check()
;

public ResultResponse login(LoginRequest authRequest)
;

public ResultResponse changePassword(ChangePasswordRequest request)
;

public Object register(RegisterRequest request)
;

}