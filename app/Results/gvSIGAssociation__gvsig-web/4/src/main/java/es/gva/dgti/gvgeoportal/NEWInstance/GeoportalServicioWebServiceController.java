package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GeoportalServicioWebServiceController {

 private GeoportalServicioWebService geoportalserviciowebservice;


@GetMapping
("/findGeoportalServicioWebsByGeoportal")
public List<GeoportalServicioWeb> findGeoportalServicioWebsByGeoportal(@RequestParam(name = "geoPortal") GeoPortal geoPortal){
  return geoportalserviciowebservice.findGeoportalServicioWebsByGeoportal(geoPortal);
}


@GetMapping
("/saveGeoportalServicioWeb")
public Object saveGeoportalServicioWeb(@RequestParam(name = "Object") Object Object){
  return geoportalserviciowebservice.saveGeoportalServicioWeb(Object);
}


}