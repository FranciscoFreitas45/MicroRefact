import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsVehicleTypeInfoRepositoryImpl implements QmsVehicleTypeInfoRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsVehicleTypeInfo> findByFlagStatusAndVehicleType(String string,String string2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByFlagStatusAndVehicleType"))
    .queryParam("string",string)
    .queryParam("string2",string2);
  List<QmsVehicleTypeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<QmsVehicleTypeInfo>.class)

 return aux;
}


public QmsVehicleTypeInfo findByVehicleTypeAndFlagStatus(String vehicleType,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByVehicleTypeAndFlagStatus"))
    .queryParam("vehicleType",vehicleType)
    .queryParam("flagStatus",flagStatus);
  QmsVehicleTypeInfo aux = restTemplate.getForObject(builder.toUriString(), QmsVehicleTypeInfo.class)

 return aux;
}


}