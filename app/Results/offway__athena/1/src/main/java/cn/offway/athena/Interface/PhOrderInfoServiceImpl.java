package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhOrderInfoService;
public class PhOrderInfoServiceImpl implements PhOrderInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public PhOrderInfo findByOrderNo(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrderNo"))
    .queryParam("orderNo",orderNo)
;  PhOrderInfo aux = restTemplate.getForObject(builder.toUriString(), PhOrderInfo.class);

 return aux;
}


}