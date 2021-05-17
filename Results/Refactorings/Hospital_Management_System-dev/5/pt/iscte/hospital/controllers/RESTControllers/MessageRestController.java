import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.services.MessageService;
@RestController
public class MessageRestController {

@Autowired
 private  Common common;

@Autowired
 private  MessageService messageService;


@GetMapping(value = "/user/messages/rest/has-unread-messages/")
public boolean hasUnreadMessages(){
    User currentUser = common.currentUser();
    return messageService.hasUnreadMessages(currentUser.getUserId());
}


}