import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhGoodsServiceImpl implements PhGoodsService{

 private RestTemplate restTemplate;

  String url = "http://1";


public PhGoods findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  PhGoods aux = restTemplate.getForObject(builder.toUriString(), PhGoods.class)

 return aux;
}


}