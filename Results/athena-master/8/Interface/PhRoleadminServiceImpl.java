import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhRoleadminServiceImpl implements PhRoleadminService{

 private RestTemplate restTemplate;

  String url = "http://11";


public List<Long> findRoleIdByAdminId(Long adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRoleIdByAdminId"))
    .queryParam("adminId",adminId);
  List<Long> aux = restTemplate.getForObject(builder.toUriString(), List<Long>.class)

 return aux;
}


}