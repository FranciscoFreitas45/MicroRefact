package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.PositionDao;
public class PositionDaoImpl implements PositionDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}