package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.LinkModel;
public class LinkModelImpl implements LinkModel{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public String list(Model model){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("model",model)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}