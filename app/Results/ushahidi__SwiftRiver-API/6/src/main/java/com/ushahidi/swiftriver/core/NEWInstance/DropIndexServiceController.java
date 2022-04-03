package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DropIndexServiceController {

 private DropIndexService dropindexservice;


@GetMapping
("/findDrops")
public List<GetDropDTO> findDrops(@RequestParam(name = "searchTerm") String searchTerm,@RequestParam(name = "count") int count,@RequestParam(name = "page") int page){
  return dropindexservice.findDrops(searchTerm,count,page);
}


}