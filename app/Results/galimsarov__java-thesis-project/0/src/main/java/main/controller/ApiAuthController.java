package main.controller;
 import main.model.request.others.EmailRequest;
import main.model.request.passwords.ChangePasswordRequest;
import main.model.request.passwords.LoginRequest;
import main.model.request.passwords.RegisterRequest;
import main.model.response.others.CaptchaResponse;
import main.model.response.results.ResultResponse;
import main.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.io.IOException;
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

@Autowired
 private  AuthService authService;


@GetMapping("/logout")
public ResultResponse logout(){
    return authService.logout();
}


@PostMapping("/restore")
public ResultResponse restore(EmailRequest request){
    return authService.restore(request);
}


@GetMapping("/captcha")
public CaptchaResponse captcha(){
    return authService.captcha();
}


@GetMapping("/check")
public ResultResponse check(){
    return authService.check();
}


@PostMapping("/login")
public ResultResponse login(LoginRequest request){
    return authService.login(request);
}


@PostMapping("/password")
public ResultResponse changePassword(ChangePasswordRequest request){
    return authService.changePassword(request);
}


@PostMapping("/register")
public Object register(RegisterRequest request){
    return authService.register(request);
}


}