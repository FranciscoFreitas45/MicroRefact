package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HonorRepositoryController {

 private HonorRepository honorrepository;


@PutMapping
("/deleteByPhone")
public void deleteByPhone(@RequestParam(name = "phone") String phone){
honorrepository.deleteByPhone(phone);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return honorrepository.save(Object);
}


@GetMapping
("/findByPhone")
public List<Honor> findByPhone(@RequestParam(name = "phone") String phone){
  return honorrepository.findByPhone(phone);
}


}