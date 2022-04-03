package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QualityMissionHisRepositoryController {

 private QualityMissionHisRepository qualitymissionhisrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qualitymissionhisrepository.save(Object);
}


}