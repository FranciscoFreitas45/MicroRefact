package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChatMessageRepositoryController {

 private ChatMessageRepository chatmessagerepository;


@GetMapping
("/findById")
public ChatMessage findById(@RequestParam(name = "id") String id){
  return chatmessagerepository.findById(id);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return chatmessagerepository.save(Object);
}


@GetMapping
("/findByAgentserviceidAndOrgi")
public Page<ChatMessage> findByAgentserviceidAndOrgi(@RequestParam(name = "agentserviceid") String agentserviceid,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "page") Pageable page){
  return chatmessagerepository.findByAgentserviceidAndOrgi(agentserviceid,orgi,page);
}


@GetMapping
("/findByContextidAndUseridAndOrgi")
public Page<ChatMessage> findByContextidAndUseridAndOrgi(@RequestParam(name = "contextid") String contextid,@RequestParam(name = "userid") String userid,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "page") Pageable page){
  return chatmessagerepository.findByContextidAndUseridAndOrgi(contextid,userid,orgi,page);
}


@GetMapping
("/findByContextidAndOrgi")
public Page<ChatMessage> findByContextidAndOrgi(@RequestParam(name = "contextid") String contextid,@RequestParam(name = "orgi") String orgi,@RequestParam(name = "page") Pageable page){
  return chatmessagerepository.findByContextidAndOrgi(contextid,orgi,page);
}


}