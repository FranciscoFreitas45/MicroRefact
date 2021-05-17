import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QiniuServiceImpl implements QiniuService{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public boolean qiniuDelete(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("qiniuDelete"))
    .queryParam("name",name);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


public boolean qiniuDelete(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("qiniuDelete"))
    .queryParam("name",name);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


public boolean qiniuDelete(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("qiniuDelete"))
    .queryParam("name",name);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


public boolean qiniuDelete(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("qiniuDelete"))
    .queryParam("name",name);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


}