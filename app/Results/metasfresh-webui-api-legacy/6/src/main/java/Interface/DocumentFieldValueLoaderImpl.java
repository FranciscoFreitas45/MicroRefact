package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentFieldValueLoaderImpl implements DocumentFieldValueLoader{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/retrieveFieldValue"))
    .queryParam("rs",rs)
    .queryParam("isDisplayColumnAvailable",isDisplayColumnAvailable)
    .queryParam("adLanguage",adLanguage)
    .queryParam("lookupDescriptor",lookupDescriptor);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}