package kr.ac.sejong.api.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UploadVidRepositoryController {

 private UploadVidRepository uploadvidrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return uploadvidrepository.save(Object);
}


@GetMapping
("/findByVidUpUser")
public List<UploadVid> findByVidUpUser(@RequestParam(name = "user") User user){
  return uploadvidrepository.findByVidUpUser(user);
}


@GetMapping
("/size")
public Object size(@RequestParam(name = "Object") Object Object){
  return uploadvidrepository.size(Object);
}


}