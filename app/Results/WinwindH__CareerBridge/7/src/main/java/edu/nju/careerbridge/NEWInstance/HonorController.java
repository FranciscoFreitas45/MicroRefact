package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HonorController {

 private HonorRepository honorrepository;


@PutMapping
("/setPhone/{id}")
public void setPhone(@PathVariable(name = "id") Integer id,@RequestParam(name = "phone") String phone){
 honorrepository.setPhone(id,phone);
}


@PutMapping
("/setHonorName/{id}")
public void setHonorName(@PathVariable(name = "id") Integer id,@RequestParam(name = "honorName") String honorName){
 honorrepository.setHonorName(id,honorName);
}


}