package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhOrderGoodsRepository;
public class PhOrderGoodsRepositoryImpl implements PhOrderGoodsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public int countByGoodsIds(List<Long> goodsIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByGoodsIds"))
    .queryParam("goodsIds",goodsIds)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}