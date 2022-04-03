package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArchivedOrderRepositoryController {

 private ArchivedOrderRepository archivedorderrepository;


@GetMapping
("/saveAll")
public List<S> saveAll(@RequestParam(name = "var1") Iterable<S> var1){
  return archivedorderrepository.saveAll(var1);
}


}