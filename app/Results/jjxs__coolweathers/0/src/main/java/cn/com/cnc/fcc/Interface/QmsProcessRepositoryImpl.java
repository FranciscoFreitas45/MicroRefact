package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsProcessRepository;
public class QmsProcessRepositoryImpl implements QmsProcessRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public QmsProcess findByFlagStatusAndId(String string,Long valueOf){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByFlagStatusAndId"))
    .queryParam("string",string)
    .queryParam("valueOf",valueOf)
;  QmsProcess aux = restTemplate.getForObject(builder.toUriString(), QmsProcess.class);

 return aux;
}


}