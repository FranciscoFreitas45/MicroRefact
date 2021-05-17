import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class AppointmentRepositoryController {

 private AppointmentRepository appointmentrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return appointmentrepository.save(Object);
}


}