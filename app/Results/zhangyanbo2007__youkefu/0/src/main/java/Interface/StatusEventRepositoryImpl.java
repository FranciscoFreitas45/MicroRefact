package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.StatusEventRepository;
public class StatusEventRepositoryImpl implements StatusEventRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public StatusEvent findById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  StatusEvent aux = restTemplate.getForObject(builder.toUriString(), StatusEvent.class);

 return aux;
}


}