package kr.ac.sejong.api.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UploadRepositoryController {

 private UploadRepository uploadrepository;


@GetMapping
("/findByUser")
public List<Upload> findByUser(@RequestParam(name = "user") User user){
  return uploadrepository.findByUser(user);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return uploadrepository.save(Object);
}


}