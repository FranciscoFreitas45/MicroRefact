package sn.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import sn.model.Person;
@RestController
@CrossOrigin
public class PersonNotificationController {

@Autowired
 private PersonNotificationService personnotificationservice;


}