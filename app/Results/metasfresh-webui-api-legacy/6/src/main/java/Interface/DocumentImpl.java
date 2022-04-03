package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentImpl implements Document{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public IDocumentEvaluatee asEvaluatee(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/asEvaluatee"))
  IDocumentEvaluatee aux = restTemplate.getForObject(builder.toUriString(), IDocumentEvaluatee.class);

 return aux;
}


public IDocumentFieldView getFieldView(String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldView"))
    .queryParam("fieldName",fieldName);
  IDocumentFieldView aux = restTemplate.getForObject(builder.toUriString(), IDocumentFieldView.class);

 return aux;
}


}