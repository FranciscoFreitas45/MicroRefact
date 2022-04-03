package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MetadataTable;
public class MetadataTableImpl implements MetadataTable{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public String getTablename(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTablename"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}