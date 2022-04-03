package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrganController {

 private Organ organ;


@GetMapping
("/isSkill")
public boolean isSkill(){
  return organ.isSkill();
}


}