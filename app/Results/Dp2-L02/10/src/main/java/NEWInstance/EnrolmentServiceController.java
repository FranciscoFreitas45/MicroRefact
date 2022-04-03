package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EnrolmentServiceController {

 private EnrolmentService enrolmentservice;


@PutMapping
("/delete")
public void delete(@RequestParam(name = "e") Enrolment e){
enrolmentservice.delete(e);
}


}