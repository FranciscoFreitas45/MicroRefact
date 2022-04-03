package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhBrandadminService;
public class PhBrandadminServiceImpl implements PhBrandadminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public List<Long> findBrandIdByAdminId(Long adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBrandIdByAdminId"))
    .queryParam("adminId",adminId)
;  List<Long> aux = restTemplate.getForObject(builder.toUriString(), List<Long>.class);

 return aux;
}


}