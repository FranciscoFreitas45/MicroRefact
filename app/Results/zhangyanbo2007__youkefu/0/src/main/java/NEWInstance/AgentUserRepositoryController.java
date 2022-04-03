package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentUserRepositoryController {

 private AgentUserRepository agentuserrepository;


@GetMapping
("/findByAgentnoAndOrgi")
public List<AgentUser> findByAgentnoAndOrgi(@RequestParam(name = "agentno") String agentno,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "sort") Sort sort){
  return agentuserrepository.findByAgentnoAndOrgi(agentno,orgi,sort);
}


@GetMapping
("/findOneByAgentnoAndStatusAndOrgi")
public AgentUser findOneByAgentnoAndStatusAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "status") String status,@RequestParam(name = "orgi") String orgi){
  return agentuserrepository.findOneByAgentnoAndStatusAndOrgi(id,status,orgi);
}


}