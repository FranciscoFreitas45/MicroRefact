package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsProductRepository;
public class QmsProductRepositoryImpl implements QmsProductRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public List<QmsProduct> findByProductNumAndMaterielId(String productNum,Integer materielId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByProductNumAndMaterielId"))
    .queryParam("productNum",productNum)
    .queryParam("materielId",materielId)
;  List<QmsProduct> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProduct>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}