package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.GestorCatalogoService;
public class GestorCatalogoServiceImpl implements GestorCatalogoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<GestorCatalogo> findAllGestorCatalogos(String sortFieldName,String sortOrder){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllGestorCatalogos"))
    .queryParam("sortFieldName",sortFieldName)
    .queryParam("sortOrder",sortOrder)
;  List<GestorCatalogo> aux = restTemplate.getForObject(builder.toUriString(), List<GestorCatalogo>.class);

 return aux;
}


}