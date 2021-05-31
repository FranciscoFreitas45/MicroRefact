import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhResourceServiceImpl implements PhResourceService{

 private RestTemplate restTemplate;

  String url = "http://13";


public List<PhResource> findByAdminId(Long adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAdminId"))
    .queryParam("adminId",adminId);
  List<PhResource> aux = restTemplate.getForObject(builder.toUriString(), List<PhResource>.class)

 return aux;
}


}