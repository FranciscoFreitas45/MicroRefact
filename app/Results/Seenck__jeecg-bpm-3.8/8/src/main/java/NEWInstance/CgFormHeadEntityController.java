package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgFormHeadEntityController {

 private CgFormHeadEntity cgformheadentity;


@GetMapping
("/getSubTableStr")
public String getSubTableStr(){
  return cgformheadentity.getSubTableStr();
}


@GetMapping
("/length")
public Object length(@RequestParam(name = "Object") Object Object){
  return cgformheadentity.length(Object);
}


@GetMapping
("/split")
public Object split(@RequestParam(name = "Object") Object Object){
  return cgformheadentity.split(Object);
}


@GetMapping
("/getContent")
public java.lang.String getContent(){
  return cgformheadentity.getContent();
}


@GetMapping
("/getTableName")
public java.lang.String getTableName(){
  return cgformheadentity.getTableName();
}


@GetMapping
("/getColumns")
public List<CgFormFieldEntity> getColumns(){
  return cgformheadentity.getColumns();
}


@PutMapping
("/setIsDbSynch")
public void setIsDbSynch(@RequestParam(name = "isDbSynch") java.lang.String isDbSynch){
cgformheadentity.setIsDbSynch(isDbSynch);
}


}