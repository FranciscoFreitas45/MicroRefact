package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhWardrobeRepository;
public class PhWardrobeRepositoryImpl implements PhWardrobeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public int deleteByGoodsIds(List<Long> goodsIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByGoodsIds"))
    .queryParam("goodsIds",goodsIds)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}