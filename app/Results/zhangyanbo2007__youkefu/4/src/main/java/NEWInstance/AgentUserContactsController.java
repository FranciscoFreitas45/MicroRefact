package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentUserContactsController {

 private AgentUserContacts agentusercontacts;

 private AgentUserContacts agentusercontacts;


@PutMapping
("/setAppid")
public void setAppid(@RequestParam(name = "appid") String appid){
agentusercontacts.setAppid(appid);
}


@PutMapping
("/setContactsid")
public void setContactsid(@RequestParam(name = "contactsid") String contactsid){
agentusercontacts.setContactsid(contactsid);
}


@PutMapping
("/setUserid")
public void setUserid(@RequestParam(name = "userid") String userid){
agentusercontacts.setUserid(userid);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
agentusercontacts.setCreatetime(createtime);
}


}