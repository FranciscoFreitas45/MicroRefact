package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.LanguageResponse;
public class LanguageResponseImpl implements LanguageResponse{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public LanguageResponse of(Language language){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("language",language)
;  LanguageResponse aux = restTemplate.getForObject(builder.toUriString(), LanguageResponse.class);

 return aux;
}


}