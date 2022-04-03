package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Message;
@RestController
@CrossOrigin
public class MessageUserController {

@Autowired
 private MessageUserService messageuserservice;


}