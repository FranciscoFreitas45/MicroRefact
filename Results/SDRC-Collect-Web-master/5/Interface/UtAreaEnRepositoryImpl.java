import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UtAreaEnRepositoryImpl implements UtAreaEnRepository{

 private RestTemplate restTemplate;

  String url = "http://4";


public List<UtAreaEn> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
  List<UtAreaEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtAreaEn>.class)

 return aux;
}


public List<UtAreaEn> findByAreaShortName(String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAreaShortName"))
    .queryParam("string",string);
  List<UtAreaEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtAreaEn>.class)

 return aux;
}


}