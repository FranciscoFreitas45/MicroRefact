package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IDocumentFieldViewImpl implements IDocumentFieldView{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String getFieldName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldName"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}