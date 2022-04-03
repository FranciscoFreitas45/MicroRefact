package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgFormFieldServiceIController {

 private CgFormFieldServiceI cgformfieldservicei;


@GetMapping
("/findByProperty")
public Object findByProperty(@RequestParam(name = "Object") Object Object){
  return cgformfieldservicei.findByProperty(Object);
}


@GetMapping
("/getEntity")
public Object getEntity(@RequestParam(name = "Object") Object Object){
  return cgformfieldservicei.getEntity(Object);
}


@GetMapping
("/getCgFormHeadByTableName")
public CgFormHeadEntity getCgFormHeadByTableName(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "version") String version){
  return cgformfieldservicei.getCgFormHeadByTableName(tableName,version);
}


}