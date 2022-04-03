package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsVehicleTypeInfoRepository;
public class QmsVehicleTypeInfoRepositoryImpl implements QmsVehicleTypeInfoRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<QmsVehicleTypeInfo> findByFlagStatusAndVehicleType(String string,String string2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByFlagStatusAndVehicleType"))
    .queryParam("string",string)
    .queryParam("string2",string2)
;  List<QmsVehicleTypeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<QmsVehicleTypeInfo>.class);

 return aux;
}


public QmsVehicleTypeInfo findByVehicleTypeAndFlagStatus(String vehicleType,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByVehicleTypeAndFlagStatus"))
    .queryParam("vehicleType",vehicleType)
    .queryParam("flagStatus",flagStatus)
;  QmsVehicleTypeInfo aux = restTemplate.getForObject(builder.toUriString(), QmsVehicleTypeInfo.class);

 return aux;
}


}