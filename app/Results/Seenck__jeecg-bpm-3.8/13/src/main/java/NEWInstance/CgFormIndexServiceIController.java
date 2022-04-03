package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgFormIndexServiceIController {

 private CgFormIndexServiceI cgformindexservicei;


@GetMapping
("/updateIndexes")
public boolean updateIndexes(@RequestParam(name = "cgFormHead") CgFormHeadEntity cgFormHead){
  return cgformindexservicei.updateIndexes(cgFormHead);
}


}