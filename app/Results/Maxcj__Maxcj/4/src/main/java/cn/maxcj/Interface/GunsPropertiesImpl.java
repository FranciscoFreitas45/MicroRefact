package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.GunsProperties;
public class GunsPropertiesImpl implements GunsProperties{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String getFileUploadPath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFileUploadPath"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}