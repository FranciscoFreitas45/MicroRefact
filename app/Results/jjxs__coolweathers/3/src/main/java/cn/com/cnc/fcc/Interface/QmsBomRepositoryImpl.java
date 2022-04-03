package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsBomRepository;
public class QmsBomRepositoryImpl implements QmsBomRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<QmsBom> findByVehicleIdAndFlagStatus(Integer vehicleType,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByVehicleIdAndFlagStatus"))
    .queryParam("vehicleType",vehicleType)
    .queryParam("flagStatus",flagStatus)
;  List<QmsBom> aux = restTemplate.getForObject(builder.toUriString(), List<QmsBom>.class);

 return aux;
}


}