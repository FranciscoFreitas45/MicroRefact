package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class SqlForFetchingLookupByIdImpl implements SqlForFetchingLookupById{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public IStringExpression toStringExpression(String joinOnColumnNameFQ){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toStringExpression"))
    .queryParam("joinOnColumnNameFQ",joinOnColumnNameFQ);
  IStringExpression aux = restTemplate.getForObject(builder.toUriString(), IStringExpression.class);

 return aux;
}


}