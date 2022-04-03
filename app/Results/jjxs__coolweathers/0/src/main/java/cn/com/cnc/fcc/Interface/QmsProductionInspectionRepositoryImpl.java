package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsProductionInspectionRepository;
public class QmsProductionInspectionRepositoryImpl implements QmsProductionInspectionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(Integer BomTechnologyId,String FlagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBomTechnologyIdAndFlagStatus"))
    .queryParam("BomTechnologyId",BomTechnologyId)
    .queryParam("FlagStatus",FlagStatus)
;  List<QmsProductionInspection> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProductionInspection>.class);

 return aux;
}


}