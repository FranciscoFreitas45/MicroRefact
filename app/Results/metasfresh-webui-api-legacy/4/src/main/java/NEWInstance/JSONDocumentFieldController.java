package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONDocumentFieldController {

 private JSONDocumentField jsondocumentfield;

 private JSONDocumentField jsondocumentfield;


@GetMapping
("/ofNameAndValue")
public JSONDocumentField ofNameAndValue(@RequestParam(name = "fieldName") String fieldName,@RequestParam(name = "jsonValue") Object jsonValue){
  return jsondocumentfield.ofNameAndValue(fieldName,jsonValue);
}


}