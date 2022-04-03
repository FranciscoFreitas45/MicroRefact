package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReporterRepositoryController {

 private ReporterRepository reporterrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return reporterrepository.save(Object);
}


}