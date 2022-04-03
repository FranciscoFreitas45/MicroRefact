package sn.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import sn.model.Message;
@RestController
@CrossOrigin
public class MessagePersonController {

@Autowired
 private MessagePersonService messagepersonservice;


}