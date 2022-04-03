package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.INoticeService;
public class INoticeServiceImpl implements INoticeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Map<String,Object>> list(String condition){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("condition",condition)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}