package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ServicioWebController {

 private ServicioWeb servicioweb;


@GetMapping
("/getNombre")
public Object getNombre(@RequestParam(name = "Object") Object Object){
  return servicioweb.getNombre(Object);
}


}