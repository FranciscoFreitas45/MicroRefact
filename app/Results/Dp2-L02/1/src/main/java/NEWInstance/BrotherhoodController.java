package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrotherhoodController {

 private Brotherhood brotherhood;

 private Brotherhood brotherhood;


@PutMapping
("/setParades")
public void setParades(@RequestParam(name = "parades") List<Parade> parades){
brotherhood.setParades(parades);
}


@PutMapping
("/setFloats")
public void setFloats(@RequestParam(name = "floats") List<Float> floats){
brotherhood.setFloats(floats);
}


@PutMapping
("/setEnrolments")
public void setEnrolments(@RequestParam(name = "enrolments") List<Enrolment> enrolments){
brotherhood.setEnrolments(enrolments);
}


}