package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.User;
@RestController
@CrossOrigin
public class UserMessageController {

@Autowired
 private UserMessageService usermessageservice;


}