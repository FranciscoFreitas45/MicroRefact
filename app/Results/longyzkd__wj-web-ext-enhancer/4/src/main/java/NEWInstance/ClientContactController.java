package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientContactController {

 private ClientContact clientcontact;

 private ClientContact clientcontact;


@PutMapping
("/setPwd")
public void setPwd(@RequestParam(name = "pwd") String pwd){
clientcontact.setPwd(pwd);
}


}