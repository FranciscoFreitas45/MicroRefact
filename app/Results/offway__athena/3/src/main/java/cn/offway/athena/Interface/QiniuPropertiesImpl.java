package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.QiniuProperties;
public class QiniuPropertiesImpl implements QiniuProperties{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public String getUrl(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUrl"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}