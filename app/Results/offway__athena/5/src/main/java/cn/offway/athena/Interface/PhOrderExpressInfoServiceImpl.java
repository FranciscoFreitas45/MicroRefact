package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhOrderExpressInfoService;
public class PhOrderExpressInfoServiceImpl implements PhOrderExpressInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public PhOrderExpressInfo findByExpressOrderNo(String expressOrderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExpressOrderNo"))
    .queryParam("expressOrderNo",expressOrderNo)
;  PhOrderExpressInfo aux = restTemplate.getForObject(builder.toUriString(), PhOrderExpressInfo.class);

 return aux;
}


public PhOrderExpressInfo save(PhOrderExpressInfo phOrderExpressInfo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("phOrderExpressInfo",phOrderExpressInfo)
;  PhOrderExpressInfo aux = restTemplate.getForObject(builder.toUriString(), PhOrderExpressInfo.class);

 return aux;
}


}