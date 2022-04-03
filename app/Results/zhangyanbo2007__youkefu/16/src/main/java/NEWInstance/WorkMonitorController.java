package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkMonitorController {

 private WorkMonitor workmonitor;

 private WorkMonitor workmonitor;


@PutMapping
("/setAgentno")
public void setAgentno(@RequestParam(name = "agentno") String agentno){
workmonitor.setAgentno(agentno);
}


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
workmonitor.setStatus(status);
}


@PutMapping
("/setAdmin")
public void setAdmin(@RequestParam(name = "admin") boolean admin){
workmonitor.setAdmin(admin);
}


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
workmonitor.setUsername(username);
}


@PutMapping
("/setExtno")
public void setExtno(@RequestParam(name = "extno") String extno){
workmonitor.setExtno(extno);
}


@PutMapping
("/setWorktype")
public void setWorktype(@RequestParam(name = "worktype") String worktype){
workmonitor.setWorktype(worktype);
}


@PutMapping
("/setDuration")
public void setDuration(@RequestParam(name = "duration") int duration){
workmonitor.setDuration(duration);
}


@PutMapping
("/setBusy")
public void setBusy(@RequestParam(name = "busy") boolean busy){
workmonitor.setBusy(busy);
}


@PutMapping
("/setFirsttime")
public void setFirsttime(@RequestParam(name = "firsttime") boolean firsttime){
workmonitor.setFirsttime(firsttime);
}


@PutMapping
("/setFirsttimes")
public void setFirsttimes(@RequestParam(name = "firsttimes") int firsttimes){
workmonitor.setFirsttimes(firsttimes);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
workmonitor.setCreatetime(createtime);
}


@PutMapping
("/setDatestr")
public void setDatestr(@RequestParam(name = "datestr") String datestr){
workmonitor.setDatestr(datestr);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
workmonitor.setName(name);
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
workmonitor.setOrgi(orgi);
}


@PutMapping
("/setSkill")
public void setSkill(@RequestParam(name = "skill") String skill){
workmonitor.setSkill(skill);
}


@PutMapping
("/setUserid")
public void setUserid(@RequestParam(name = "userid") String userid){
workmonitor.setUserid(userid);
}


}