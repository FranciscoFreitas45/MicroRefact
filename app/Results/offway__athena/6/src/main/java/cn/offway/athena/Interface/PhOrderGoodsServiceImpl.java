package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhOrderGoodsService;
public class PhOrderGoodsServiceImpl implements PhOrderGoodsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<PhOrderGoods> findByBrandId(String brandId,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBrandId"))
    .queryParam("brandId",brandId)
    .queryParam("page",page)
;  Page<PhOrderGoods> aux = restTemplate.getForObject(builder.toUriString(), Page<PhOrderGoods>.class);

 return aux;
}


}