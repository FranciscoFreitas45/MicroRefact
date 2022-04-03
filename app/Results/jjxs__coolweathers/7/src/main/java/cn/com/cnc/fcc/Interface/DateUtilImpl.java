package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.DateUtil;
public class DateUtilImpl implements DateUtil{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public ZonedDateTime getDBNowDate(String dataFormat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDBNowDate"))
    .queryParam("dataFormat",dataFormat)
;  ZonedDateTime aux = restTemplate.getForObject(builder.toUriString(), ZonedDateTime.class);

 return aux;
}


}