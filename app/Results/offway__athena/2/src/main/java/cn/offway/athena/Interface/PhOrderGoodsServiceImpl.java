package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhOrderGoodsService;
public class PhOrderGoodsServiceImpl implements PhOrderGoodsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<PhOrderGoods> findByOrderNo(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrderNo"))
    .queryParam("orderNo",orderNo)
;  List<PhOrderGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhOrderGoods>.class);

 return aux;
}


}