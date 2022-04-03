package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserBookResponseController {

 private UserBookResponse userbookresponse;


@GetMapping
("/of")
public UserBookResponse of(@RequestParam(name = "user") UserBook user){
  return userbookresponse.of(user);
}


}