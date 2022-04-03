package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.CoverService;
public class CoverServiceImpl implements CoverService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Cover getCoverByBookId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCoverByBookId"))
    .queryParam("id",id)
;  Cover aux = restTemplate.getForObject(builder.toUriString(), Cover.class);

 return aux;
}


}