package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgFormFieldServiceI;
public class CgFormFieldServiceIImpl implements CgFormFieldServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public boolean updateVersion(String formId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateVersion"))
    .queryParam("formId",formId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}