import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.repository.RbacUserRepository;
import cn.com.cnc.fcc.security.jwt.JWTFilter;
import cn.com.cnc.fcc.security.jwt.TokenProvider;
import cn.com.cnc.fcc.web.rest.UserJWTController.JWTToken;
import cn.com.cnc.fcc.web.rest.errors.PAPIException;
import cn.com.cnc.fcc.web.rest.vm.LoginPAPI;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/papi")
public class PAPIJWTController {

 private  TokenProvider tokenProvider;

 private  RbacUserRepository rbacUserRepository;

@Qualifier("AuthenticationManagerImpl")
 private  AuthenticationManager authenticationManager;


@PostMapping("/getalluser")
@Timed
public ResponseEntity<RbacUser> getalluser(String id){
    JSONObject jsonObject = JSONObject.parseObject(id);
    String identifying = jsonObject.getString("id");
    Optional<RbacUser> rbacUser = rbacUserRepository.findById(Long.valueOf(identifying).longValue());
    return ResponseUtil.wrapOrNotFound(rbacUser);
}


@PostMapping("/gettoken")
@Timed
public ResponseEntity<JWTToken> authorize(LoginPAPI LoginPAPI){
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(LoginPAPI.getUsername(), LoginPAPI.getPassword());
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("identifying", 1);
    authenticationToken.setDetails(jsonObject.toString());
    Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = "";
    String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    if (!"".equals(authorities)) {
        jwt = tokenProvider.createToken(authentication, false);
    } else {
        throw new PAPIException(10001);
    }
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
}


}