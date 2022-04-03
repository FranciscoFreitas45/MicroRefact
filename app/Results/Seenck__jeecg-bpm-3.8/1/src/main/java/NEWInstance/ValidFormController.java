package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ValidFormController {

 private ValidForm validform;

 private ValidForm validform;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
validform.setStatus(status);
}


}