package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IInStorageServiceController {

 private IInStorageService iinstorageservice;


@PutMapping
("/save")
public void save(@RequestParam(name = "inStorage") InStorage inStorage){
iinstorageservice.save(inStorage);
}


}