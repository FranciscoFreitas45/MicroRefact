package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfVistasPredefinidasServiceController {

 private ConfVistasPredefinidasService confvistaspredefinidasservice;


@GetMapping
("/findConfVistasPredefinidas")
public Object findConfVistasPredefinidas(@RequestParam(name = "Object") Object Object){
  return confvistaspredefinidasservice.findConfVistasPredefinidas(Object);
}


@GetMapping
("/saveConfVistasPredefinidas")
public Object saveConfVistasPredefinidas(@RequestParam(name = "Object") Object Object){
  return confvistaspredefinidasservice.saveConfVistasPredefinidas(Object);
}


@GetMapping
("/findConfVistasPredefinidasesByGeoPortal")
public List<ConfVistasPredefinidas> findConfVistasPredefinidasesByGeoPortal(@RequestParam(name = "geoPortal") GeoPortal geoPortal){
  return confvistaspredefinidasservice.findConfVistasPredefinidasesByGeoPortal(geoPortal);
}


@GetMapping
("/deleteConfVistasPredefinidas")
public Object deleteConfVistasPredefinidas(@RequestParam(name = "Object") Object Object){
  return confvistaspredefinidasservice.deleteConfVistasPredefinidas(Object);
}


}