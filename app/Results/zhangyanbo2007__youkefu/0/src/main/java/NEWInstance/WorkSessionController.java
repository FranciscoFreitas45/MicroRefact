package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkSessionController {

 private WorkSession worksession;

 private WorkSession worksession;


@PutMapping
("/setBegintime")
public void setBegintime(@RequestParam(name = "begintime") Date begintime){
worksession.setBegintime(begintime);
}


@PutMapping
("/setAgent")
public void setAgent(@RequestParam(name = "agent") String agent){
worksession.setAgent(agent);
}


@PutMapping
("/setAgentno")
public void setAgentno(@RequestParam(name = "agentno") String agentno){
worksession.setAgentno(agentno);
}


@PutMapping
("/setAdmin")
public void setAdmin(@RequestParam(name = "admin") boolean admin){
worksession.setAdmin(admin);
}


@PutMapping
("/setFirsttime")
public void setFirsttime(@RequestParam(name = "firsttime") boolean firsttime){
worksession.setFirsttime(firsttime);
}


@PutMapping
("/setIpaddr")
public void setIpaddr(@RequestParam(name = "ipaddr") String ipaddr){
worksession.setIpaddr(ipaddr);
}


@PutMapping
("/setHostname")
public void setHostname(@RequestParam(name = "hostname") String hostname){
worksession.setHostname(hostname);
}


@PutMapping
("/setUserid")
public void setUserid(@RequestParam(name = "userid") String userid){
worksession.setUserid(userid);
}


@PutMapping
("/setClientid")
public void setClientid(@RequestParam(name = "clientid") String clientid){
worksession.setClientid(clientid);
}


@PutMapping
("/setSessionid")
public void setSessionid(@RequestParam(name = "sessionid") String sessionid){
worksession.setSessionid(sessionid);
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
worksession.setOrgi(orgi);
}


@PutMapping
("/setDatestr")
public void setDatestr(@RequestParam(name = "datestr") String datestr){
worksession.setDatestr(datestr);
}


}