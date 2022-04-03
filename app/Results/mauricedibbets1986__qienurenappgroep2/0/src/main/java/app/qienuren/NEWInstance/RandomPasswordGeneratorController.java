package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RandomPasswordGeneratorController {

 private RandomPasswordGenerator randompasswordgenerator;


@GetMapping
("/generatePassayPassword")
public String generatePassayPassword(){
  return randompasswordgenerator.generatePassayPassword();
}


}