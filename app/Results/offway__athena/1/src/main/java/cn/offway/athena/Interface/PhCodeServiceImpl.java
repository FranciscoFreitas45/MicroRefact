package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhCodeService;
public class PhCodeServiceImpl implements PhCodeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public PhCode findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  PhCode aux = restTemplate.getForObject(builder.toUriString(), PhCode.class);

 return aux;
}


public PhCode save(PhCode phCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("phCode",phCode)
;  PhCode aux = restTemplate.getForObject(builder.toUriString(), PhCode.class);

 return aux;
}


}