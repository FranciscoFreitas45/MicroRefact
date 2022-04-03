package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import pl.edu.wat.wcy.pz.restaurantServer.repository.UserRepository;
@RestController
@CrossOrigin
public class UserController {

 private UserRepository userrepository;


@PutMapping
("/setPassword/{id}")
public void setPassword(@PathVariable(name = "id") Long id,@RequestParam(name = "password") String password){
 userrepository.setPassword(id,password);
}


@PutMapping
("/setUserId/{id}")
public void setUserId(@PathVariable(name = "id") Long id,@RequestParam(name = "userId") Long userId){
 userrepository.setUserId(id,userId);
}


}