import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhGoodsImageServiceImpl implements PhGoodsImageService{

 private RestTemplate restTemplate;

  String url = "http://9";


public int deleteByGoodsIds(List<Long> goodsIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByGoodsIds"))
    .queryParam("goodsIds",goodsIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public List<PhGoodsImage> findByGoodsId(Long goodsId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGoodsId"))
    .queryParam("goodsId",goodsId);
  List<PhGoodsImage> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoodsImage>.class)

 return aux;
}


public void delete(List<PhGoodsImage> phGoodsImages){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("phGoodsImages",phGoodsImages);

  restTemplate.put(builder.toUriString(), null)



public List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("phGoodsImages",phGoodsImages);
  List<PhGoodsImage> aux = restTemplate.getForObject(builder.toUriString(), List<PhGoodsImage>.class)

 return aux;
}


public PhGoodsImage findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  PhGoodsImage aux = restTemplate.getForObject(builder.toUriString(), PhGoodsImage.class)

 return aux;
}


}