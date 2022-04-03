package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PbxHostRepositoryController {

 private PbxHostRepository pbxhostrepository;


@GetMapping
("/findById")
public PbxHost findById(@RequestParam(name = "id") String id){
  return pbxhostrepository.findById(id);
}


}