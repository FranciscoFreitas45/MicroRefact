import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhOrderGoodsServiceImpl implements PhOrderGoodsService{

 private RestTemplate restTemplate;

  String url = "http://1";


public List<PhOrderGoods> findByOrderNo(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrderNo"))
    .queryParam("orderNo",orderNo);
  List<PhOrderGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhOrderGoods>.class)

 return aux;
}


public PhOrderGoods findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  PhOrderGoods aux = restTemplate.getForObject(builder.toUriString(), PhOrderGoods.class)

 return aux;
}


public PhOrderGoods save(PhOrderGoods phOrderGoods){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("phOrderGoods",phOrderGoods);
  PhOrderGoods aux = restTemplate.getForObject(builder.toUriString(), PhOrderGoods.class)

 return aux;
}


public List<PhOrderGoods> findNormalByOrderNo(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findNormalByOrderNo"))
    .queryParam("orderNo",orderNo);
  List<PhOrderGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhOrderGoods>.class)

 return aux;
}


}