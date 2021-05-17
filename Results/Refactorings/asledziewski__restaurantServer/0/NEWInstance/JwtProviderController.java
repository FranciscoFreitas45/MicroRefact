import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
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