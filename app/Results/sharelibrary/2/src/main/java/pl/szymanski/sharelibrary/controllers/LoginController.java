package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymanski.sharelibrary.requests.LoginRequest;
import pl.szymanski.sharelibrary.response.UserLoginResponse;
import pl.szymanski.sharelibrary.services.ports.UserService;
import org.springframework.http.HttpStatus.OK;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

 private  UserService userService;


@PostMapping
public ResponseEntity<Object> login(LoginRequest loginRequest){
    return new ResponseEntity<>(UserLoginResponse.of(userService.getUserByEmailOrUserName(loginRequest.getUserNameOrEmail()), userService.getJwt(loginRequest)), OK);
}


}