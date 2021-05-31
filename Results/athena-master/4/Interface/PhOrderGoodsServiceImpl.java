import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhOrderGoodsServiceImpl implements PhOrderGoodsService{

 private RestTemplate restTemplate;

  String url = "http://1";


public Page<PhOrderGoods> findByBrandId(String brandId,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBrandId"))
    .queryParam("brandId",brandId)
    .queryParam("page",page);
  Page<PhOrderGoods> aux = restTemplate.getForObject(builder.toUriString(), Page<PhOrderGoods>.class)

 return aux;
}


}