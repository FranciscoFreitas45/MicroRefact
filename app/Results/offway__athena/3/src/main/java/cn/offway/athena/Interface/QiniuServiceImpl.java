package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.QiniuService;
public class QiniuServiceImpl implements QiniuService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public String token(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/token"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}