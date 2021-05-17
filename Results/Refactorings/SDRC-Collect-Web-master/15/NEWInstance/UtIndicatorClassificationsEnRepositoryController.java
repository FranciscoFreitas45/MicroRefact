import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UtIndicatorClassificationsEnRepositoryController {

 private UtIndicatorClassificationsEnRepository utindicatorclassificationsenrepository;


@GetMapping
("/getSubsectorId")
public int getSubsectorId(@RequestParam(name = "subSectorName") String subSectorName){
  return utindicatorclassificationsenrepository.getSubsectorId(subSectorName);
}


@GetMapping
("/getSubsector")
public List<UtIndicatorClassificationsEn> getSubsector(){
  return utindicatorclassificationsenrepository.getSubsector();
}


}