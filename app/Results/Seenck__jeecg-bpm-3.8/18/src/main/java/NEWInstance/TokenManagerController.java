package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TokenManagerController {

 private TokenManager tokenmanager;


@GetMapping
("/createToken")
public String createToken(@RequestParam(name = "user") TSUser user){
  return tokenmanager.createToken(user);
}


@PutMapping
("/deleteToken")
public void deleteToken(@RequestParam(name = "username") String username){
tokenmanager.deleteToken(username);
}


}