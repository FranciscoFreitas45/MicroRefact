package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DataBaseServiceController {

 private DataBaseService databaseservice;


@GetMapping
("/getCgformEnhanceJavaEntityByFormId")
public List<CgformEnhanceJavaEntity> getCgformEnhanceJavaEntityByFormId(@RequestParam(name = "formId") String formId){
  return databaseservice.getCgformEnhanceJavaEntityByFormId(formId);
}


}