package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtDataModelController {

 private UtDataModel utdatamodel;

 private UtDataModel utdatamodel;


@GetMapping
("/compareTo")
public int compareTo(@RequestParam(name = "o") UtDataModel o){
  return utdatamodel.compareTo(o);
}


}