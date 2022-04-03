package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GeoPortalServiceController {

 private GeoPortalService geoportalservice;


@GetMapping
("/findPublicGeoPortalesByUrlEquals")
public TypedQuery<GeoPortal> findPublicGeoPortalesByUrlEquals(@RequestParam(name = "url") String url){
  return geoportalservice.findPublicGeoPortalesByUrlEquals(url);
}


@GetMapping
("/findGeoPortalesByUrlEquals")
public TypedQuery<GeoPortal> findGeoPortalesByUrlEquals(@RequestParam(name = "url") String url,@RequestParam(name = "sortFieldName") String sortFieldName,@RequestParam(name = "sortOrder") String sortOrder){
  return geoportalservice.findGeoPortalesByUrlEquals(url,sortFieldName,sortOrder);
}


@GetMapping
("/getComponentsAndInformationByGeoportal")
public Map<String,Object> getComponentsAndInformationByGeoportal(@RequestParam(name = "geoportal") GeoPortal geoportal){
  return geoportalservice.getComponentsAndInformationByGeoportal(geoportal);
}


@GetMapping
("/findAllGeoPortales")
public Object findAllGeoPortales(@RequestParam(name = "Object") Object Object){
  return geoportalservice.findAllGeoPortales(Object);
}


@GetMapping
("/saveGeoPortal")
public Object saveGeoPortal(@RequestParam(name = "Object") Object Object){
  return geoportalservice.saveGeoPortal(Object);
}


@GetMapping
("/findGeoPortal")
public Object findGeoPortal(@RequestParam(name = "Object") Object Object){
  return geoportalservice.findGeoPortal(Object);
}


}