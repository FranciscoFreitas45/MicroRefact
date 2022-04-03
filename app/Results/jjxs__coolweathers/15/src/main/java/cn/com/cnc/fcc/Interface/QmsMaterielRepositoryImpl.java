package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsMaterielRepository;
public class QmsMaterielRepositoryImpl implements QmsMaterielRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<QmsMateriel> findByIdAndFlagStatus(Long valueOf,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndFlagStatus"))
    .queryParam("valueOf",valueOf)
    .queryParam("string",string)
;  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class);

 return aux;
}


public List<QmsMateriel> findByMaterielCdAndFlagStatus(String materielCd,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMaterielCdAndFlagStatus"))
    .queryParam("materielCd",materielCd)
    .queryParam("flagStatus",flagStatus)
;  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class);

 return aux;
}


}