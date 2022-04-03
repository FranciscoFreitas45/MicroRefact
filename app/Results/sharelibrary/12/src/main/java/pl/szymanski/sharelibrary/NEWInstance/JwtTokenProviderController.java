package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JwtTokenProviderController {

 private JwtTokenProvider jwttokenprovider;


@GetMapping
("/generateToken")
public String generateToken(@RequestParam(name = "authentication") Authentication authentication){
  return jwttokenprovider.generateToken(authentication);
}


}