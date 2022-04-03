package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientController {

 private Client client;


@GetMapping
("/result")
public List<String> result(@RequestParam(name = "check") String check){
  return client.result(check);
}


}