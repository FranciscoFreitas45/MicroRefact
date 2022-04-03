package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgFormVersionDao;
public class CgFormVersionDaoImpl implements CgFormVersionDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public CgFormHeadEntity getCgFormById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCgFormById"))
    .queryParam("id",id)
;  CgFormHeadEntity aux = restTemplate.getForObject(builder.toUriString(), CgFormHeadEntity.class);

 return aux;
}


}