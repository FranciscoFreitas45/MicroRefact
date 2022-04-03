package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.logistics.stock.entity.InDetailed;
@RestController
@CrossOrigin
public class InDetailedInStorageController {

@Autowired
 private InDetailedInStorageService indetailedinstorageservice;


@GetMapping
("/InStorage/{id}/InDetailed/getInDetaileds")
public List<InDetailed> getInDetaileds(@PathVariable(name="id") String inStorageId){
return indetailedinstorageservice.getInDetaileds(inStorageId);
}


@PutMapping
("/InStorage/{id}/InDetailed/setInDetaileds")
public void setInDetaileds(@PathVariable(name="id") String inStorageId,@RequestBody List<InDetailed> inDetaileds){
indetailedinstorageservice.setInDetaileds(inStorageId,inDetaileds);
}


}