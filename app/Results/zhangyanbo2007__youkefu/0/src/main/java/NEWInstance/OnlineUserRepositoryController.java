package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OnlineUserRepositoryController {

 private OnlineUserRepository onlineuserrepository;


@GetMapping
("/findByOrgiAndStatus")
public List<Object> findByOrgiAndStatus(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "status") String status){
  return onlineuserrepository.findByOrgiAndStatus(orgi,status);
}


@GetMapping
("/findByOrgiAndAgentnoAndCreatetimeRange")
public List<Object> findByOrgiAndAgentnoAndCreatetimeRange(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "agentno") String agentno,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end){
  return onlineuserrepository.findByOrgiAndAgentnoAndCreatetimeRange(orgi,agentno,start,end);
}


@GetMapping
("/countByAgentForAgentUser")
public Long countByAgentForAgentUser(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "status") String status,@RequestParam(name = "agentno") String agentno,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end){
  return onlineuserrepository.countByAgentForAgentUser(orgi,status,agentno,start,end);
}


@GetMapping
("/countByAgentForAvagTime")
public Long countByAgentForAvagTime(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "status") String status,@RequestParam(name = "agentno") String agentno,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end){
  return onlineuserrepository.countByAgentForAvagTime(orgi,status,agentno,start,end);
}


@GetMapping
("/findByOrgiAndCreatetimeRange")
public List<Object> findByOrgiAndCreatetimeRange(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "model") String model,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end){
  return onlineuserrepository.findByOrgiAndCreatetimeRange(orgi,model,start,end);
}


@GetMapping
("/findByOrgiAndCreatetimeRangeForAgent")
public List<Object> findByOrgiAndCreatetimeRangeForAgent(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end){
  return onlineuserrepository.findByOrgiAndCreatetimeRangeForAgent(orgi,start,end);
}


@GetMapping
("/findByOrgiAndCreatetimeRangeForClient")
public List<Object> findByOrgiAndCreatetimeRangeForClient(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end,@RequestParam(name = "channel") String channel){
  return onlineuserrepository.findByOrgiAndCreatetimeRangeForClient(orgi,start,end,channel);
}


@GetMapping
("/findByOrgiAndCreatetimeRangeForBrowser")
public List<Object> findByOrgiAndCreatetimeRangeForBrowser(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end,@RequestParam(name = "channel") String channel){
  return onlineuserrepository.findByOrgiAndCreatetimeRangeForBrowser(orgi,start,end,channel);
}


}