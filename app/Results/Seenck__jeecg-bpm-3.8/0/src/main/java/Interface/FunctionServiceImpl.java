package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.FunctionService;
public class FunctionServiceImpl implements FunctionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public AjaxJson delFunction(String functionId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delFunction"))
    .queryParam("functionId",functionId)
;  AjaxJson aux = restTemplate.getForObject(builder.toUriString(), AjaxJson.class);

 return aux;
}


}