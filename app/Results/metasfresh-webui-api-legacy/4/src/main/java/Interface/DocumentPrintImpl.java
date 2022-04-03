package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentPrintImpl implements DocumentPrint{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object builder(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object filename(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/filename"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}