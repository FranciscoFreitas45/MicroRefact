package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JwtConfigController {

 private JwtConfig jwtconfig;


@GetMapping
("/getSecret")
public Object getSecret(@RequestParam(name = "Object") Object Object){
  return jwtconfig.getSecret(Object);
}


@GetMapping
("/getExpiration")
public Object getExpiration(@RequestParam(name = "Object") Object Object){
  return jwtconfig.getExpiration(Object);
}


@GetMapping
("/getHeader")
public Object getHeader(@RequestParam(name = "Object") Object Object){
  return jwtconfig.getHeader(Object);
}


@GetMapping
("/getPrefix")
public Object getPrefix(@RequestParam(name = "Object") Object Object){
  return jwtconfig.getPrefix(Object);
}


}