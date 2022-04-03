package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhGoodsImageService;
public class PhGoodsImageServiceImpl implements PhGoodsImageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<PhGoodsImage> findByGoodsId(Long goodsId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGoodsId"))
    .queryParam("goodsId",goodsId)
;  List<PhGoodsImage> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoodsImage>.class);

 return aux;
}


}