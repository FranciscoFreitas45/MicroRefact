package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.LanguageRepository;
public class LanguageRepositoryImpl implements LanguageRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Optional<Language> getLanguageById(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLanguageById"))
    .queryParam("id",id)
;  Optional<Language> aux = restTemplate.getForObject(builder.toUriString(), Optional<Language>.class);

 return aux;
}


}