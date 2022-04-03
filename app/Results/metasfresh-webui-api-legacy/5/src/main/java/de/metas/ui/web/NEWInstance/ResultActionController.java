package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResultActionController {

 private ResultAction resultaction;


@GetMapping
("/getClass")
public Object getClass(@RequestParam(name = "Object") Object Object){
  return resultaction.getClass(Object);
}


}