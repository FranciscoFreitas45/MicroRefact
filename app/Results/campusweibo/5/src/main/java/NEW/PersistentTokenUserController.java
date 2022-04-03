package NEW;
 import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PersistentTokenUserController {

 private PersistentTokenUserService persistenttokenuserservice;


@PutMapping
("/User/{id}/PersistentToken/setPersistentTokens")
public void setPersistentTokens(@PathVariable(name="id") Long id,@RequestBody Set<PersistentToken> persistentTokens){
persistenttokenuserservice.setPersistentTokens(id,persistentTokens);
}


@GetMapping
("/User/{id}/PersistentToken/getPersistentTokens")
public Set<PersistentToken> getPersistentTokens(@PathVariable(name="id") Long id){
return persistenttokenuserservice.getPersistentTokens(id);
}


}