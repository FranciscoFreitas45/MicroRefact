package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Language;
public class LanguageResponse {

 private  Integer id;

 private  String name;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public LanguageResponse of(Language language){
    return new LanguageResponse(language.getId(), language.getName());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("language",language)
;
LanguageResponse aux = restTemplate.getForObject(builder.toUriString(),LanguageResponse.class);
return aux;
}


}