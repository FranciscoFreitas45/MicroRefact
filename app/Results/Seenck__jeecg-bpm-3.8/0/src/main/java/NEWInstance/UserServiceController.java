package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userservice.save(Object);
}


@PutMapping
("/saveOrUpdate")
public void saveOrUpdate(@RequestParam(name = "user") TSUser user,@RequestParam(name = "orgIds") String[] orgIds,@RequestParam(name = "roleIds") String[] roleIds){
userservice.saveOrUpdate(user,orgIds,roleIds);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return userservice.delete(Object);
}


}