package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.ProcessService;
public class ProcessServiceImpl implements ProcessService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void user(int page,int size,Model model){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/user"))
    .queryParam("page",page)
    .queryParam("size",size)
    .queryParam("model",model)
;
  restTemplate.put(builder.toUriString(), null);
}


}