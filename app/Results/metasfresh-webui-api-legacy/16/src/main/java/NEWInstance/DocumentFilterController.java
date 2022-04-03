package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFilterController {

 private DocumentFilter documentfilter;

 private DocumentFilter documentfilter;


@GetMapping
("/builder")
public Builder builder(){
  return documentfilter.builder();
}


@GetMapping
("/singleParameterFilter")
public DocumentFilter singleParameterFilter(@RequestParam(name = "filterId") String filterId,@RequestParam(name = "fieldName") String fieldName,@RequestParam(name = "operator") Operator operator,@RequestParam(name = "value") Object value){
  return documentfilter.singleParameterFilter(filterId,fieldName,operator,value);
}


@GetMapping
("/hasParameters")
public boolean hasParameters(){
  return documentfilter.hasParameters();
}


}