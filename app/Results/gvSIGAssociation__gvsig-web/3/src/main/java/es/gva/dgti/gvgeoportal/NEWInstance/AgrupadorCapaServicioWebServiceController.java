package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgrupadorCapaServicioWebServiceController {

 private AgrupadorCapaServicioWebService agrupadorcapaserviciowebservice;


@GetMapping
("/findServicesByGroup")
public TypedQuery<Long> findServicesByGroup(@RequestParam(name = "agrupadorCapaId") Long agrupadorCapaId){
  return agrupadorcapaserviciowebservice.findServicesByGroup(agrupadorCapaId);
}


@GetMapping
("/findAgrupadorCapaServicioWeb")
public Object findAgrupadorCapaServicioWeb(@RequestParam(name = "Object") Object Object){
  return agrupadorcapaserviciowebservice.findAgrupadorCapaServicioWeb(Object);
}


}