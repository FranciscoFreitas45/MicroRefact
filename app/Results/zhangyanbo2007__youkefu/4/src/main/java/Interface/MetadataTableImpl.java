package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MetadataTable;
public class MetadataTableImpl implements MetadataTable{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<TableProperties> getTableproperty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableproperty"))
;  List<TableProperties> aux = restTemplate.getForObject(builder.toUriString(), List<TableProperties>.class);

 return aux;
}


public Object indexOf(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/indexOf"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}