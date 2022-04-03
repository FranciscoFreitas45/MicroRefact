package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LookupDataSourceController {

 private LookupDataSource lookupdatasource;


@GetMapping
("/findById")
public LookupValue findById(@RequestParam(name = "id") Object id){
  return lookupdatasource.findById(id);
}


}