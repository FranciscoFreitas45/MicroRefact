package pl.szymanski.sharelibrary.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.entity.User;
@RestController
@CrossOrigin
public class UserChatMessageController {

@Autowired
 private UserChatMessageService userchatmessageservice;


}