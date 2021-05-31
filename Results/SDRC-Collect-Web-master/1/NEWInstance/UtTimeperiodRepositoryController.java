import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UtTimeperiodRepositoryController {

 private UtTimeperiodRepository uttimeperiodrepository;


@GetMapping
("/findByStartDateAndEndDateAndPeriodicity")
public UtTimeperiod findByStartDateAndEndDateAndPeriodicity(@RequestParam(name = "startDate") Date startDate,@RequestParam(name = "endDate") Date endDate,@RequestParam(name = "string") String string){
  return uttimeperiodrepository.findByStartDateAndEndDateAndPeriodicity(startDate,endDate,string);
}


@GetMapping
("/save")
public UtTimeperiod save(@RequestParam(name = "timeperiod") UtTimeperiod timeperiod){
  return uttimeperiodrepository.save(timeperiod);
}


@GetMapping
("/findByPeriodicity")
public List<UtTimeperiod> findByPeriodicity(@RequestParam(name = "string") String string){
  return uttimeperiodrepository.findByPeriodicity(string);
}


}