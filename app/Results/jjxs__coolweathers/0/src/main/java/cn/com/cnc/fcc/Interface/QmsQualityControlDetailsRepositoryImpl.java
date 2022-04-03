package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsQualityControlDetailsRepository;
public class QmsQualityControlDetailsRepositoryImpl implements QmsQualityControlDetailsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://18";


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public QmsQualityControlDetails findByIdAndFlagStatus(Long id,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndFlagStatus"))
    .queryParam("id",id)
    .queryParam("string",string)
;  QmsQualityControlDetails aux = restTemplate.getForObject(builder.toUriString(), QmsQualityControlDetails.class);

 return aux;
}


public List<QmsQualityControlDetails> findByBomTechnologyIdAndFlagStatus(Integer id,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBomTechnologyIdAndFlagStatus"))
    .queryParam("id",id)
    .queryParam("flagStatus",flagStatus)
;  List<QmsQualityControlDetails> aux = restTemplate.getForObject(builder.toUriString(), List<QmsQualityControlDetails>.class);

 return aux;
}


}