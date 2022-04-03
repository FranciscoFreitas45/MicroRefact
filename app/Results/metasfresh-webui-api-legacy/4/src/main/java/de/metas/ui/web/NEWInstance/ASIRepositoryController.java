package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ASIRepositoryController {

 private ASIRepository asirepository;


@GetMapping
("/loadReadonly")
public ASIDocument loadReadonly(@RequestParam(name = "attributeSetInstanceId") AttributeSetInstanceId attributeSetInstanceId){
  return asirepository.loadReadonly(attributeSetInstanceId);
}


}