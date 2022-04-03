package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BoxServiceController {

 private BoxService boxservice;


@GetMapping
("/createSystem")
public Box createSystem(){
  return boxservice.createSystem();
}


@GetMapping
("/saveSystem")
public Box saveSystem(@RequestParam(name = "box") Box box){
  return boxservice.saveSystem(box);
}


@PutMapping
("/deleteAllBoxes")
public void deleteAllBoxes(){
boxservice.deleteAllBoxes();
}


}