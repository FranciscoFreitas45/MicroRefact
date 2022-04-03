package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentServiceSummaryController {

 private AgentServiceSummary agentservicesummary;

 private AgentServiceSummary agentservicesummary;


@PutMapping
("/setCreater")
public void setCreater(@RequestParam(name = "creater") String creater){
agentservicesummary.setCreater(creater);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
agentservicesummary.setCreatetime(createtime);
}


@PutMapping
("/setAgentno")
public void setAgentno(@RequestParam(name = "agentno") String agentno){
agentservicesummary.setAgentno(agentno);
}


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
agentservicesummary.setUsername(username);
}


@PutMapping
("/setAgentusername")
public void setAgentusername(@RequestParam(name = "agentusername") String agentusername){
agentservicesummary.setAgentusername(agentusername);
}


@PutMapping
("/setChannel")
public void setChannel(@RequestParam(name = "channel") String channel){
agentservicesummary.setChannel(channel);
}


@PutMapping
("/setLogindate")
public void setLogindate(@RequestParam(name = "logindate") Date logindate){
agentservicesummary.setLogindate(logindate);
}


@PutMapping
("/setContactsid")
public void setContactsid(@RequestParam(name = "contactsid") String contactsid){
agentservicesummary.setContactsid(contactsid);
}


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
agentservicesummary.setEmail(email);
}


@PutMapping
("/setPhonenumber")
public void setPhonenumber(@RequestParam(name = "phonenumber") String phonenumber){
agentservicesummary.setPhonenumber(phonenumber);
}


}