package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhAddressService;
public class PhAddressServiceImpl implements PhAddressService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public PhAddress findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  PhAddress aux = restTemplate.getForObject(builder.toUriString(), PhAddress.class);

 return aux;
}


}