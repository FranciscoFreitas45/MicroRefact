package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ServiceSummaryRepositoryController {

 private ServiceSummaryRepository servicesummaryrepository;


@GetMapping
("/findByOrgiAndUserid")
public Page<AgentServiceSummary> findByOrgiAndUserid(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "userid") String userid,@RequestParam(name = "pageable") Pageable pageable){
  return servicesummaryrepository.findByOrgiAndUserid(orgi,userid,pageable);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return servicesummaryrepository.save(Object);
}


@GetMapping
("/findByIdAndOrgi")
public AgentServiceSummary findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi){
  return servicesummaryrepository.findByIdAndOrgi(id,orgi);
}


}