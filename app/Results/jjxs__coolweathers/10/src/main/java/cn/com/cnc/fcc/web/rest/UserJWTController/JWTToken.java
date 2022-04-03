package cn.com.cnc.fcc.web.rest.UserJWTController;
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
public class JWTToken {

 private  String idToken;

JWTToken(String idToken) {
    this.idToken = idToken;
}
public void setIdToken(String idToken){
    this.idToken = idToken;
}


@JsonProperty("id_token")
public String getIdToken(){
    return idToken;
}


}