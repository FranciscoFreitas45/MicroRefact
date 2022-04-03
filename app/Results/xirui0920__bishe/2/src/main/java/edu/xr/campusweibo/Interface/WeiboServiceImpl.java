package edu.xr.campusweibo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.Interface.WeiboService;
public class WeiboServiceImpl implements WeiboService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public int getWeiboNum(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeiboNum"))
    .queryParam("id",id)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Weibo> getAllWeibo(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllWeibo"))
    .queryParam("id",id)
;  List<Weibo> aux = restTemplate.getForObject(builder.toUriString(), List<Weibo>.class);

 return aux;
}


}