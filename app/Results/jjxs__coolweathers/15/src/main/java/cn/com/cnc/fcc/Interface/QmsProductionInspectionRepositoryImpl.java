package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsProductionInspectionRepository;
public class QmsProductionInspectionRepositoryImpl implements QmsProductionInspectionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(Integer s,String m){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMaterielIdAndSerialNumber"))
    .queryParam("s",s)
    .queryParam("m",m)
;  List<QmsProductionInspection> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProductionInspection>.class);

 return aux;
}


}