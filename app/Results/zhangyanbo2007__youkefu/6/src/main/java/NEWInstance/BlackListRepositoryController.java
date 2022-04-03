package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlackListRepositoryController {

 private BlackListRepository blacklistrepository;


@GetMapping
("/findByUseridAndOrgi")
public BlackEntity findByUseridAndOrgi(@RequestParam(name = "userid") String userid,@RequestParam(name = "orgi") String orgi){
  return blacklistrepository.findByUseridAndOrgi(userid,orgi);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return blacklistrepository.save(Object);
}


@GetMapping
("/findByEndtimeLessThan")
public Page<BlackEntity> findByEndtimeLessThan(@RequestParam(name = "endtime") Date endtime,@RequestParam(name = "page") Pageable page){
  return blacklistrepository.findByEndtimeLessThan(endtime,page);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return blacklistrepository.delete(Object);
}


}