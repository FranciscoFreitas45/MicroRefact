package es.gva.dgti.gvgeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GestorCatalogoServiceController {

 private GestorCatalogoService gestorcatalogoservice;


@GetMapping
("/findAllGestorCatalogos")
public List<GestorCatalogo> findAllGestorCatalogos(@RequestParam(name = "sortFieldName") String sortFieldName,@RequestParam(name = "sortOrder") String sortOrder){
  return gestorcatalogoservice.findAllGestorCatalogos(sortFieldName,sortOrder);
}


}