package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComandaBusinessController {

 private ComandaBusiness comandabusiness;


@GetMapping
("/eliminaComanda")
public Boolean eliminaComanda(@RequestParam(name = "comandaBotiga") ComandaBotiga comandaBotiga){
  return comandabusiness.eliminaComanda(comandaBotiga);
}


}