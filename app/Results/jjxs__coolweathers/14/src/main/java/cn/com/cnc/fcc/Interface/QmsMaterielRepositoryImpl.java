package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsMaterielRepository;
public class QmsMaterielRepositoryImpl implements QmsMaterielRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<QmsMateriel> findByUseUnitId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUseUnitId"))
    .queryParam("s",s)
;  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class);

 return aux;
}


public List<QmsMateriel> findByPackgeUnitId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPackgeUnitId"))
    .queryParam("s",s)
;  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class);

 return aux;
}


}