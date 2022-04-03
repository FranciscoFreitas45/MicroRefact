package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import upce.semprace.eshop.repository.NakupRepository;
 import upce.semprace.eshop.entity.Nakup;
  import java.util.*;
  import upce.semprace.eshop.DTO.*;
  import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class NakupRepositoryController {
@Autowired
 private NakupRepository nakuprepository;


@GetMapping
("NakuRepository/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return nakuprepository.save((Nakup) Object);
}

@GetMapping
("NakuRepository/findAll")
public List<Nakup> findAll(){
  return nakuprepository.findAll();
}


}