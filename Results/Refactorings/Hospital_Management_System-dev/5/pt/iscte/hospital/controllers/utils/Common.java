import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.services.MessageService;
import pt.iscte.hospital.services.user.UserService;
@Component
public class Common {

 private  UserService userService;

 private  MessageService messageService;


public ModelMap sideNavMap(){
    ModelMap modelMap = new ModelMap();
    boolean hasUnreadMessages = false;
    if (currentUser() != null) {
        hasUnreadMessages = messageService.hasUnreadMessages(currentUser().getUserId());
    }
    modelMap.put("user_logged", currentUser());
    modelMap.put("hasUnreadMessages", hasUnreadMessages);
    return modelMap;
}


public User currentUser(){
    return userService.currentUser();
}


}