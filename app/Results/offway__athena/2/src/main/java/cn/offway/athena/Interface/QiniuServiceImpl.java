package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.QiniuService;
public class QiniuServiceImpl implements QiniuService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public boolean qiniuDelete(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/qiniuDelete"))
    .queryParam("name",name)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}