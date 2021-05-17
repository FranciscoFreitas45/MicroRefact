import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class TokenProviderController {

 private TokenProvider tokenprovider;


@GetMapping
("/createToken")
public String createToken(@RequestParam(name = "authentication") Authentication authentication,@RequestParam(name = "rememberMe") boolean rememberMe){
  return tokenprovider.createToken(authentication,rememberMe);
}


}