package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsMaterielRepository;
public class QmsMaterielRepositoryImpl implements QmsMaterielRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Optional<QmsMateriel> findQmsMaterielByMaterielCdAndFlagStatus(String materielCd,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findQmsMaterielByMaterielCdAndFlagStatus"))
    .queryParam("materielCd",materielCd)
    .queryParam("flagStatus",flagStatus)
;  Optional<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), Optional<QmsMateriel>.class);

 return aux;
}


}