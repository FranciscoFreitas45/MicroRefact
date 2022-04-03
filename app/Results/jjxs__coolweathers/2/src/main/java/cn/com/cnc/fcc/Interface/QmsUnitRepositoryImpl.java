package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsUnitRepository;
public class QmsUnitRepositoryImpl implements QmsUnitRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://14";


public List<QmsUnit> findByUnitCd(String s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUnitCd"))
    .queryParam("s",s)
;  List<QmsUnit> aux = restTemplate.getForObject(builder.toUriString(), List<QmsUnit>.class);

 return aux;
}


}