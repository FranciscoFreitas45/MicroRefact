import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class IndicatorRepositoryController {

 private IndicatorRepository indicatorrepository;


@GetMapping
("/findByIndicator_NId")
public UtIndicatorEn findByIndicator_NId(@RequestParam(name = "indicator_NId") int indicator_NId){
  return indicatorrepository.findByIndicator_NId(indicator_NId);
}


}