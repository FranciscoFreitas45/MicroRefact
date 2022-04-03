package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientManagerController {

 private ClientManager clientmanager;


@GetMapping
("/getAllClient")
public Collection<Client> getAllClient(){
  return clientmanager.getAllClient();
}


@PutMapping
("/addClinet")
public void addClinet(@RequestParam(name = "sessionId") String sessionId,@RequestParam(name = "client") Client client){
clientmanager.addClinet(sessionId,client);
}


}