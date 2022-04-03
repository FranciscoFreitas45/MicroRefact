package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ImportFileController {

 private ImportFile importfile;

 private ImportFile importfile;


@PutMapping
("/setEntityName")
public void setEntityName(@RequestParam(name = "entityName") String entityName){
importfile.setEntityName(entityName);
}


@PutMapping
("/setFileName")
public void setFileName(@RequestParam(name = "fileName") String fileName){
importfile.setFileName(fileName);
}


@PutMapping
("/setEntityClass")
public void setEntityClass(@RequestParam(name = "entityClass") Class entityClass){
importfile.setEntityClass(entityClass);
}


}