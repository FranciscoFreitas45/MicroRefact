package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrganRepositoryController {

 private OrganRepository organrepository;


@GetMapping
("/findByOrgi")
public List<Organ> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return organrepository.findByOrgi(orgi);
}


@GetMapping
("/findAll")
public List<Organ> findAll(@RequestParam(name = "spec") Specification<Organ> spec){
  return organrepository.findAll(spec);
}


@GetMapping
("/findByIdAndOrgi")
public Organ findByIdAndOrgi(@RequestParam(name = "paramString") String paramString,@RequestParam(name = "orgi") String orgi){
  return organrepository.findByIdAndOrgi(paramString,orgi);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return organrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return organrepository.delete(Object);
}


@GetMapping
("/findByOrgiAndOrgid")
public List<Organ> findByOrgiAndOrgid(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "orgid") String orgid){
  return organrepository.findByOrgiAndOrgid(orgi,orgid);
}


@GetMapping
("/findByOrgiAndParent")
public List<Organ> findByOrgiAndParent(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "parent") String parent){
  return organrepository.findByOrgiAndParent(orgi,parent);
}


}