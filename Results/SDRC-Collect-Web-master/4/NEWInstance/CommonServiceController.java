import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CommonServiceController {

 private CommonService commonservice;


@GetMapping
("/getMinValue")
public Double getMinValue(@RequestParam(name = "data") List<Object[]> data){
  return commonservice.getMinValue(data);
}


@GetMapping
("/getMaxValue")
public Double getMaxValue(@RequestParam(name = "data") List<Object[]> data){
  return commonservice.getMaxValue(data);
}


@GetMapping
("/populateLegends")
public List<ValueObject> populateLegends(@RequestParam(name = "data") List<Object[]> data,@RequestParam(name = "indicatorId") String indicatorId){
  return commonservice.populateLegends(data,indicatorId);
}


@PutMapping
("/setCssForDataModel")
public void setCssForDataModel(@RequestParam(name = "list") List<ValueObject> list,@RequestParam(name = "data") UtDataModel data,@RequestParam(name = "value") Double value,@RequestParam(name = "indicatorId") String indicatorId){
commonservice.setCssForDataModel(list,data,value,indicatorId);
}


}