package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.SprintService;
public class SprintServiceImpl implements SprintService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Sprint> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Sprint> aux = restTemplate.getForObject(builder.toUriString(), List<Sprint>.class);

 return aux;
}


}