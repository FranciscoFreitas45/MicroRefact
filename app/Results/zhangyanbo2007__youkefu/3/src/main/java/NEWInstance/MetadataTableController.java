package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MetadataTableController {

 private MetadataTable metadatatable;


@GetMapping
("/getTableproperty")
public List<TableProperties> getTableproperty(){
  return metadatatable.getTableproperty();
}


@GetMapping
("/indexOf")
public Object indexOf(@RequestParam(name = "Object") Object Object){
  return metadatatable.indexOf(Object);
}


@GetMapping
("/getTablename")
public String getTablename(){
  return metadatatable.getTablename();
}


}