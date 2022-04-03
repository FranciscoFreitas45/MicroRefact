package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhGoodsService;
public class PhGoodsServiceImpl implements PhGoodsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public PhGoods findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  PhGoods aux = restTemplate.getForObject(builder.toUriString(), PhGoods.class);

 return aux;
}


public List<PhGoods> findAll(String name,Object value,String brandId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("name",name)
    .queryParam("value",value)
    .queryParam("brandId",brandId)
;  List<PhGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoods>.class);

 return aux;
}


}