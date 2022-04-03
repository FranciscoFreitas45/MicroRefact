package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgformButtonServiceIController {

 private CgformButtonServiceI cgformbuttonservicei;


@GetMapping
("/getCgformButtonListByFormId")
public List<CgformButtonEntity> getCgformButtonListByFormId(@RequestParam(name = "formId") String formId){
  return cgformbuttonservicei.getCgformButtonListByFormId(formId);
}


}