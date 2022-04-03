package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhRoleadminService;
public class PhRoleadminServiceImpl implements PhRoleadminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public List<Long> findRoleIdByAdminId(Long adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRoleIdByAdminId"))
    .queryParam("adminId",adminId)
;  List<Long> aux = restTemplate.getForObject(builder.toUriString(), List<Long>.class);

 return aux;
}


}