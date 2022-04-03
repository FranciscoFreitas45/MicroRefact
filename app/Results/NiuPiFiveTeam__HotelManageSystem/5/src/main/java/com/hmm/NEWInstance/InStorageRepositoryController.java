package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InStorageRepositoryController {

 private InStorageRepository instoragerepository;


@GetMapping
("/findInStorageOrderByDay")
public Float findInStorageOrderByDay(@RequestParam(name = "dateString") String dateString){
  return instoragerepository.findInStorageOrderByDay(dateString);
}


}