package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUEditorViewRepositoryImpl implements HUEditorViewRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public HUEditorRow retrieveForHUId(HuId huId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/retrieveForHUId"))
    .queryParam("huId",huId);
  HUEditorRow aux = restTemplate.getForObject(builder.toUriString(), HUEditorRow.class);

 return aux;
}


}