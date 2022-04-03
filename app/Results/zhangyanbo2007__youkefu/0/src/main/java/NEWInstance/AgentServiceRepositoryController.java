package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentServiceRepositoryController {

 private AgentServiceRepository agentservicerepository;


@GetMapping
("/findByOrgiAndSatisfaction")
public Page<AgentService> findByOrgiAndSatisfaction(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "satisfaction") boolean satisfaction,@RequestParam(name = "paramPageable") Pageable paramPageable){
  return agentservicerepository.findByOrgiAndSatisfaction(orgi,satisfaction,paramPageable);
}


@GetMapping
("/findAll")
public List<AgentService> findAll(@RequestParam(name = "spec") Specification<AgentService> spec){
  return agentservicerepository.findAll(spec);
}


@GetMapping
("/findByIdAndOrgi")
public AgentService findByIdAndOrgi(@RequestParam(name = "paramString") String paramString,@RequestParam(name = "orgi") String orgi){
  return agentservicerepository.findByIdAndOrgi(paramString,orgi);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return agentservicerepository.save(Object);
}


}