package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.security.jwt.JWTFilter;
import cn.com.cnc.fcc.security.jwt.TokenProvider;
import cn.com.cnc.fcc.web.rest.vm.LoginVM;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
@RestController
@RequestMapping("/api")
public class UserJWTController {

 private  TokenProvider tokenProvider;

@Qualifier("AuthenticationManagerImpl")
 private  AuthenticationManager authenticationManager;

 private  String idToken;

public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManagerImp) {
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManagerImp;
}
public void setIdToken(String idToken){
    this.idToken = idToken;
}


@JsonProperty("id_token")
public String getIdToken(){
    return idToken;
}


@PostMapping("/authenticate")
@Timed
public ResponseEntity<JWTToken> authorize(LoginVM loginVM){
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
    Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
    String jwt = tokenProvider.createToken(authentication, rememberMe);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
}


}