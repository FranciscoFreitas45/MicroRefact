import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhGoodsServiceImpl implements PhGoodsService{

 private RestTemplate restTemplate;

  String url = "http://1";


public Page<PhGoods> findByPage(String name,Long brandId,String isOffway,List<Long> brandIds,String status,String type,String category,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPage"))
    .queryParam("name",name)
    .queryParam("brandId",brandId)
    .queryParam("isOffway",isOffway)
    .queryParam("brandIds",brandIds)
    .queryParam("status",status)
    .queryParam("type",type)
    .queryParam("category",category)
    .queryParam("page",page);
  Page<PhGoods> aux = restTemplate.getForObject(builder.toUriString(), Page<PhGoods>.class)

 return aux;
}


public List<PhGoods> save(List<PhGoods> phGoods){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("phGoods",phGoods);
  List<PhGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoods>.class)

 return aux;
}


public PhGoods findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  PhGoods aux = restTemplate.getForObject(builder.toUriString(), PhGoods.class)

 return aux;
}


public List<PhGoods> findAll(String name,Object value,String brandId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("name",name)
    .queryParam("value",value)
    .queryParam("brandId",brandId);
  List<PhGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoods>.class)

 return aux;
}


public boolean imagesDelete(Long goodsImageId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/imagesDelete"))
    .queryParam("goodsImageId",goodsImageId);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


public List<PhGoods> findByBrandId(Long brandId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBrandId"))
    .queryParam("brandId",brandId);
  List<PhGoods> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoods>.class)

 return aux;
}


public String delete(List<Long> goodsIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("goodsIds",goodsIds);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}