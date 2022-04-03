package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LinkModelController {

 private LinkModel linkmodel;


@GetMapping
("/list")
public String list(@RequestParam(name = "model") Model model){
  return linkmodel.list(model);
}


}