package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuickTypeRepositoryController {

 private QuickTypeRepository quicktyperepository;


@GetMapping
("/findByOrgiAndQuicktype")
public List<QuickType> findByOrgiAndQuicktype(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "quicktype") String quicktype){
  return quicktyperepository.findByOrgiAndQuicktype(orgi,quicktype);
}


@GetMapping
("/findByOrgiAndQuicktypeAndCreater")
public List<QuickType> findByOrgiAndQuicktypeAndCreater(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "quicktype") String quicktype,@RequestParam(name = "creater") String creater){
  return quicktyperepository.findByOrgiAndQuicktypeAndCreater(orgi,quicktype,creater);
}


@GetMapping
("/findByIdAndOrgi")
public QuickType findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi){
  return quicktyperepository.findByIdAndOrgi(id,orgi);
}


@GetMapping
("/countByOrgiAndNameAndParentid")
public int countByOrgiAndNameAndParentid(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "name") String name,@RequestParam(name = "parentid") String parentid){
  return quicktyperepository.countByOrgiAndNameAndParentid(orgi,name,parentid);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return quicktyperepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return quicktyperepository.delete(Object);
}


}