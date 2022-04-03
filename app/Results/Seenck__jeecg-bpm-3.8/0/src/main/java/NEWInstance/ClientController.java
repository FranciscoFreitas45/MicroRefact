package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientController {

 private Client client;

 private Client client;


@PutMapping
("/setUser")
public void setUser(@RequestParam(name = "user") TSUser user){
client.setUser(user);
}


}