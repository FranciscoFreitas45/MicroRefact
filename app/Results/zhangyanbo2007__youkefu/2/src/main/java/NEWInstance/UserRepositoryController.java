package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findByOrgiAndDatastatus")
public Page<User> findByOrgiAndDatastatus(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "b") boolean b,@RequestParam(name = "pageRequest") Pageable pageRequest){
  return userrepository.findByOrgiAndDatastatus(orgi,b,pageRequest);
}


@GetMapping
("/findAll")
public List<User> findAll(@RequestParam(name = "spec") Specification<User> spec){
  return userrepository.findAll(spec);
}


@GetMapping
("/findByOrgiAndAgentAndDatastatus")
public List<User> findByOrgiAndAgentAndDatastatus(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "agent") boolean agent,@RequestParam(name = "status") boolean status){
  return userrepository.findByOrgiAndAgentAndDatastatus(orgi,agent,status);
}


@GetMapping
("/findByOrganInAndAgentAndDatastatus")
public List<User> findByOrganInAndAgentAndDatastatus(@RequestParam(name = "organIdList") List<String> organIdList,@RequestParam(name = "b") boolean b,@RequestParam(name = "status") boolean status){
  return userrepository.findByOrganInAndAgentAndDatastatus(organIdList,b,status);
}


@GetMapping
("/findByOrgidAndAgentAndDatastatusAndUsertype")
public Page<User> findByOrgidAndAgentAndDatastatusAndUsertype(@RequestParam(name = "orgid") String orgid,@RequestParam(name = "agent") boolean agent,@RequestParam(name = "datastatus") boolean datastatus,@RequestParam(name = "type") String type,@RequestParam(name = "pageRequest") Pageable pageRequest){
  return userrepository.findByOrgidAndAgentAndDatastatusAndUsertype(orgid,agent,datastatus,type,pageRequest);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


@GetMapping
("/getOne")
public Object getOne(@RequestParam(name = "Object") Object Object){
  return userrepository.getOne(Object);
}


@GetMapping
("/findByUsernameAndDatastatus")
public User findByUsernameAndDatastatus(@RequestParam(name = "username") String username,@RequestParam(name = "datastatus") boolean datastatus){
  return userrepository.findByUsernameAndDatastatus(username,datastatus);
}


@GetMapping
("/findByEmailAndDatastatus")
public User findByEmailAndDatastatus(@RequestParam(name = "email") String email,@RequestParam(name = "datastatus") boolean datastatus){
  return userrepository.findByEmailAndDatastatus(email,datastatus);
}


@GetMapping
("/findByMobileAndDatastatus")
public User findByMobileAndDatastatus(@RequestParam(name = "mobile") String mobile,@RequestParam(name = "datastatus") boolean datastatus){
  return userrepository.findByMobileAndDatastatus(mobile,datastatus);
}


@GetMapping
("/findByOrgi")
public List<User> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return userrepository.findByOrgi(orgi);
}


@GetMapping
("/findByDatastatusAndOrgi")
public Page<User> findByDatastatusAndOrgi(@RequestParam(name = "datastatus") boolean datastatus,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "paramPageable") Pageable paramPageable){
  return userrepository.findByDatastatusAndOrgi(datastatus,orgi,paramPageable);
}


@GetMapping
("/findByDatastatusAndOrgiAndUsernameLike")
public Page<User> findByDatastatusAndOrgiAndUsernameLike(@RequestParam(name = "datastatus") boolean datastatus,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "username") String username,@RequestParam(name = "paramPageable") Pageable paramPageable){
  return userrepository.findByDatastatusAndOrgiAndUsernameLike(datastatus,orgi,username,paramPageable);
}


@GetMapping
("/findByIdAndOrgi")
public Page<User> findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "paramPageable") Pageable paramPageable){
  return userrepository.findByIdAndOrgi(id,orgi,paramPageable);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return userrepository.delete(Object);
}


@GetMapping
("/findById")
public User findById(@RequestParam(name = "id") String id){
  return userrepository.findById(id);
}


@GetMapping
("/findByOrgidAndAgentAndDatastatus")
public List<User> findByOrgidAndAgentAndDatastatus(@RequestParam(name = "orgid") String orgid,@RequestParam(name = "agent") boolean agent,@RequestParam(name = "datastatus") boolean datastatus){
  return userrepository.findByOrgidAndAgentAndDatastatus(orgid,agent,datastatus);
}


@GetMapping
("/findByDatastatusAndOrgiAndOrgid")
public Page<User> findByDatastatusAndOrgiAndOrgid(@RequestParam(name = "b") boolean b,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "orgid") String orgid,@RequestParam(name = "pageRequest") Pageable pageRequest){
  return userrepository.findByDatastatusAndOrgiAndOrgid(b,orgi,orgid,pageRequest);
}


}