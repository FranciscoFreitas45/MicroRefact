package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CapasServicioWebServiceController {

 private CapasServicioWebService capasserviciowebservice;


@GetMapping
("/findCapasServicioWebsByServicioWeb")
public List<CapasServicioWeb> findCapasServicioWebsByServicioWeb(@RequestParam(name = "servicioWeb") ServicioWeb servicioWeb){
  return capasserviciowebservice.findCapasServicioWebsByServicioWeb(servicioWeb);
}


}