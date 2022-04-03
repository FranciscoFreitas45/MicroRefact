package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PositionServiceController {

 private PositionService positionservice;


@GetMapping
("/findAll")
public List<Position> findAll(){
  return positionservice.findAll();
}


}