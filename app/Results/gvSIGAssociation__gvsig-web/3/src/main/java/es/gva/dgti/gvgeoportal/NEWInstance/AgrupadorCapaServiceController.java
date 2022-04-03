package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgrupadorCapaServiceController {

 private AgrupadorCapaService agrupadorcapaservice;


@GetMapping
("/findAgrupadorCapa")
public Object findAgrupadorCapa(@RequestParam(name = "Object") Object Object){
  return agrupadorcapaservice.findAgrupadorCapa(Object);
}


}