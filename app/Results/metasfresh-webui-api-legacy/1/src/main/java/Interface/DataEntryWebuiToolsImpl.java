package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DataEntryWebuiToolsImpl implements DataEntryWebuiTools{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String computeFieldName(DataEntryFieldId dataEntryFieldId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/computeFieldName"))
    .queryParam("dataEntryFieldId",dataEntryFieldId);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}