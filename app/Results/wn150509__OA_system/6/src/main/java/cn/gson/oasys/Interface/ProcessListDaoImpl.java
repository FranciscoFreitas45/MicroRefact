package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.ProcessListDao;
public class ProcessListDaoImpl implements ProcessListDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<ProcessList> findlastthree(long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findlastthree"))
    .queryParam("userid",userid)
;  List<ProcessList> aux = restTemplate.getForObject(builder.toUriString(), List<ProcessList>.class);

 return aux;
}


}