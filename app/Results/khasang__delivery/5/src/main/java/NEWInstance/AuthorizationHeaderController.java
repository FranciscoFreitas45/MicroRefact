package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthorizationHeaderController {

 private AuthorizationHeader authorizationheader;

 private AuthorizationHeader authorizationheader;


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "value") String value){
authorizationheader.setPassword(value);
}


}