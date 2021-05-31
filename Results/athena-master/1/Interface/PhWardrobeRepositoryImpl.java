import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhWardrobeRepositoryImpl implements PhWardrobeRepository{

 private RestTemplate restTemplate;

  String url = "http://10";


public int deleteByGoodsIds(List<Long> goodsIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByGoodsIds"))
    .queryParam("goodsIds",goodsIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}