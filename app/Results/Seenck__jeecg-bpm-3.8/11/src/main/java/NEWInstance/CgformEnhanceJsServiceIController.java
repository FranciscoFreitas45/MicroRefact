package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgformEnhanceJsServiceIController {

 private CgformEnhanceJsServiceI cgformenhancejsservicei;


@GetMapping
("/getCgformEnhanceJsByTypeFormId")
public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(@RequestParam(name = "cgJsType") String cgJsType,@RequestParam(name = "formId") String formId){
  return cgformenhancejsservicei.getCgformEnhanceJsByTypeFormId(cgJsType,formId);
}


}