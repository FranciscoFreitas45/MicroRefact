package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ConfigurationService;
public class ConfigurationServiceImpl implements ConfigurationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<String> getSpamWords(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpamWords"))
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public Boolean isStringSpam(String s,List<String> spamWords){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isStringSpam"))
    .queryParam("s",s)
    .queryParam("spamWords",spamWords)
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public Double computeScore(Actor a){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/computeScore"))
    .queryParam("a",a)
;  Double aux = restTemplate.getForObject(builder.toUriString(), Double.class);

 return aux;
}


public Boolean isActorSuspicious(Actor a){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isActorSuspicious"))
    .queryParam("a",a);
  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public Configuration getConfiguration(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getConfiguration"))
  Configuration aux = restTemplate.getForObject(builder.toUriString(), Configuration.class);

 return aux;
}


}