package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlackEntityController {

 private BlackEntity blackentity;

 private BlackEntity blackentity;


@PutMapping
("/setControltime")
public void setControltime(@RequestParam(name = "controltime") int controltime){
blackentity.setControltime(controltime);
}


@PutMapping
("/setAgentuser")
public void setAgentuser(@RequestParam(name = "agentuser") String agentuser){
blackentity.setAgentuser(agentuser);
}


@PutMapping
("/setUserid")
public void setUserid(@RequestParam(name = "userid") String userid){
blackentity.setUserid(userid);
}


@PutMapping
("/setCreater")
public void setCreater(@RequestParam(name = "creater") String creater){
blackentity.setCreater(creater);
}


@PutMapping
("/setSessionid")
public void setSessionid(@RequestParam(name = "sessionid") String sessionid){
blackentity.setSessionid(sessionid);
}


@PutMapping
("/setAgentserviceid")
public void setAgentserviceid(@RequestParam(name = "agentserviceid") String agentserviceid){
blackentity.setAgentserviceid(agentserviceid);
}


@PutMapping
("/setChannel")
public void setChannel(@RequestParam(name = "channel") String channel){
blackentity.setChannel(channel);
}


}