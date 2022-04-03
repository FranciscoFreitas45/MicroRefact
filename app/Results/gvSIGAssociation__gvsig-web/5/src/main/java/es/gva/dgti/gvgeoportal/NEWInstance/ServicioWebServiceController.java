package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ServicioWebServiceController {

 private ServicioWebService serviciowebservice;


@GetMapping
("/getSelectedInfoLayersNames")
public String getSelectedInfoLayersNames(@RequestParam(name = "listadoCapasServicioWeb") Collection<CapasServicioWeb> listadoCapasServicioWeb,@RequestParam(name = "isLayerName") boolean isLayerName){
  return serviciowebservice.getSelectedInfoLayersNames(listadoCapasServicioWeb,isLayerName);
}


@GetMapping
("/getSelectedStyles")
public String getSelectedStyles(@RequestParam(name = "listadoCapasServicioWeb") Collection<CapasServicioWeb> listadoCapasServicioWeb){
  return serviciowebservice.getSelectedStyles(listadoCapasServicioWeb);
}


@GetMapping
("/getLayersAndStylesOrderByLayersName")
public Map<String,String> getLayersAndStylesOrderByLayersName(@RequestParam(name = "servicioWeb") ServicioWeb servicioWeb){
  return serviciowebservice.getLayersAndStylesOrderByLayersName(servicioWeb);
}


@GetMapping
("/findServicesNotInIdListAndNoGroup")
public TypedQuery<ServicioWeb> findServicesNotInIdListAndNoGroup(@RequestParam(name = "idList") List<Long> idList,@RequestParam(name = "agrupadorCapa") AgrupadorCapa agrupadorCapa){
  return serviciowebservice.findServicesNotInIdListAndNoGroup(idList,agrupadorCapa);
}


@GetMapping
("/findServicesInIdList")
public TypedQuery<ServicioWeb> findServicesInIdList(@RequestParam(name = "idList") List<Long> idList){
  return serviciowebservice.findServicesInIdList(idList);
}


@GetMapping
("/findServicesByNoGroup")
public TypedQuery<ServicioWeb> findServicesByNoGroup(@RequestParam(name = "agrupadorCapa") AgrupadorCapa agrupadorCapa){
  return serviciowebservice.findServicesByNoGroup(agrupadorCapa);
}


@GetMapping
("/getResultList")
public Object getResultList(@RequestParam(name = "Object") Object Object){
  return serviciowebservice.getResultList(Object);
}


@GetMapping
("/findAllServiciosWeb")
public Object findAllServiciosWeb(@RequestParam(name = "Object") Object Object){
  return serviciowebservice.findAllServiciosWeb(Object);
}


@GetMapping
("/findServicioWeb")
public Object findServicioWeb(@RequestParam(name = "Object") Object Object){
  return serviciowebservice.findServicioWeb(Object);
}


}