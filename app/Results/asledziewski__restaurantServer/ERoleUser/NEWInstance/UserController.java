import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserController {

 private UserRepository userrepository;


@PutMapping
("/setPassword")
public void setPassword(@PathVariable(name = "id") Long id,@RequestParam(name = "password") String password){
 userrepository.setPassword(id,password);
}


@PutMapping
("/setUserId")
public void setUserId(@PathVariable(name = "id") Long id,@RequestParam(name = "userId") Long userId){
 userrepository.setUserId(id,userId);
}


}