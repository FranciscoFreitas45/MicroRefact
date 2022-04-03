package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ContactsController {

 private Contacts contacts;

 private Contacts contacts;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") String id){
contacts.setId(id);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
contacts.setName(name);
}


@PutMapping
("/setPhone")
public void setPhone(@RequestParam(name = "phone") String phone){
contacts.setPhone(phone);
}


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
contacts.setEmail(email);
}


@PutMapping
("/setMemo")
public void setMemo(@RequestParam(name = "memo") String memo){
contacts.setMemo(memo);
}


}