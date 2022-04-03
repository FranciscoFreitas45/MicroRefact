package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InDetailedRepositoryController {

 private InDetailedRepository indetailedrepository;


@GetMapping
("/findInStorageDetailedByInAll")
public Page<InStorageDetailedDTO> findInStorageDetailedByInAll(@RequestParam(name = "inStorageId") InStorage inStorageId,@RequestParam(name = "pageable") Pageable pageable){
  return indetailedrepository.findInStorageDetailedByInAll(inStorageId,pageable);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return indetailedrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return indetailedrepository.save(Object);
}


}