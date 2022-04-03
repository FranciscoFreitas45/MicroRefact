package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSDictTableConfigServiceIController {

 private TSDictTableConfigServiceI tsdicttableconfigservicei;


@GetMapping
("/checkDictAuth")
public boolean checkDictAuth(@RequestParam(name = "dictionary") String dictionary,@RequestParam(name = "dictCondition") String dictCondition){
  return tsdicttableconfigservicei.checkDictAuth(dictionary,dictCondition);
}


@GetMapping
("/getDictText")
public Object getDictText(@RequestParam(name = "dictionary") String dictionary,@RequestParam(name = "dictCondition") String dictCondition,@RequestParam(name = "value") String value){
  return tsdicttableconfigservicei.getDictText(dictionary,dictCondition,value);
}


}