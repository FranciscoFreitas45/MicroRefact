package edu.xr.campusweibo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.domain.PersistentToken;
@RestController
@CrossOrigin
public class PersistentTokenUserController {

@Autowired
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