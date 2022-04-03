package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhAddressBrandService;
public class PhAddressBrandServiceImpl implements PhAddressBrandService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public PhAddressBrand findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  PhAddressBrand aux = restTemplate.getForObject(builder.toUriString(), PhAddressBrand.class);

 return aux;
}


}