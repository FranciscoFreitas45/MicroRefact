package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
  import upce.semprace.eshop.repository.NakoupenaPolozkaRepository;
  import upce.semprace.eshop.entity.NakoupenaPolozka;
   import java.util.*;
  import upce.semprace.eshop.DTO.*;
  import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class NakoupenaPolozkaRepositoryController {
@Autowired
 private NakoupenaPolozkaRepository nakoupenapolozkarepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return nakoupenapolozkarepository.save((NakoupenaPolozka)Object);
}

@GetMapping
("/findAll")
public List<NakoupenaPolozka> findAll(){
  return nakoupenapolozkarepository.findAll();
}



}