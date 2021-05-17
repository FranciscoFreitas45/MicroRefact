import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class TravelServiceController {

 private TravelService travelservice;


@GetMapping
("/findTotalTravelAllowance")
public float findTotalTravelAllowance(@RequestParam(name = "userName") String userName){
  return travelservice.findTotalTravelAllowance(userName);
}


@GetMapping
("/findTatalPersonTravel")
public Integer findTatalPersonTravel(){
  return travelservice.findTatalPersonTravel();
}


@GetMapping
("/findByyearAndOntudytimetravel")
public List<Map<Object,Object>> findByyearAndOntudytimetravel(@RequestParam(name = "year") Integer year,@RequestParam(name = "userName") String userName){
  return travelservice.findByyearAndOntudytimetravel(year,userName);
}


@GetMapping
("/findtravel")
public List<Map<Object,Object>> findtravel(@RequestParam(name = "year") Integer year){
  return travelservice.findtravel(year);
}


}