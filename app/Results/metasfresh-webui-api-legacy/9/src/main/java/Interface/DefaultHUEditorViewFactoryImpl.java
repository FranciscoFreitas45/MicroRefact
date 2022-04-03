package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DefaultHUEditorViewFactoryImpl implements DefaultHUEditorViewFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object getSqlViewBinding(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSqlViewBinding"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}