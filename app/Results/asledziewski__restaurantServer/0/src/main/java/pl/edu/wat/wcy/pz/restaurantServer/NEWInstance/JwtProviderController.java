package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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