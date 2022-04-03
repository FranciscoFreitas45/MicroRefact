package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.pz.restaurantServer.security.jwt.JwtProvider;
 import org.springframework.security.core.Authentication;
@RestController
@CrossOrigin
public class JwtProviderController {

 private JwtProvider jwtprovider;


@GetMapping
("/generateJwtToken")
public String generateJwtToken(@RequestParam(name = "authentication") Authentication authentication){
  return jwtprovider.generateJwtToken(authentication);
}


}