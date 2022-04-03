package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.NotificationService;
public class NotificationServiceImpl implements NotificationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public ServiceResponse<ResponseDataMessage> saveNotificationSettings(Person person,NotificationSettingRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveNotificationSettings"))
    .queryParam("person",person)
    .queryParam("request",request)
;  ServiceResponse<ResponseDataMessage> aux = restTemplate.getForObject(builder.toUriString(), ServiceResponse<ResponseDataMessage>.class);

 return aux;
}


}