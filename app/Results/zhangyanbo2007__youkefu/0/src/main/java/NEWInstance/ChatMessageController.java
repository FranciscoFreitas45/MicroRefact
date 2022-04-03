package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChatMessageController {

 private ChatMessage chatmessage;

 private ChatMessage chatmessage;


@PutMapping
("/setUsetime")
public void setUsetime(@RequestParam(name = "usetime") Date usetime){
chatmessage.setUsetime(usetime);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") String type){
chatmessage.setType(type);
}


@PutMapping
("/setUserid")
public void setUserid(@RequestParam(name = "userid") String userid){
chatmessage.setUserid(userid);
}


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") String id){
chatmessage.setId(id);
}


@PutMapping
("/setUsession")
public void setUsession(@RequestParam(name = "usession") String usession){
chatmessage.setUsession(usession);
}


@PutMapping
("/setCalltype")
public void setCalltype(@RequestParam(name = "calltype") String calltype){
chatmessage.setCalltype(calltype);
}


@PutMapping
("/setContextid")
public void setContextid(@RequestParam(name = "contextid") String contextid){
chatmessage.setContextid(contextid);
}


}