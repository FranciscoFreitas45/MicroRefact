package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewProfileIdImpl implements ViewProfileId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public boolean isNull(ViewProfileId profileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isNull"))
    .queryParam("profileId",profileId);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public ViewProfileId fromJson(String profileIdStr){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromJson"))
    .queryParam("profileIdStr",profileIdStr);
  ViewProfileId aux = restTemplate.getForObject(builder.toUriString(), ViewProfileId.class);

 return aux;
}


}