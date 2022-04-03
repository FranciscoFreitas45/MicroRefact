package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.AttachService;
public class AttachServiceImpl implements AttachService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Integer updateatt(String attname,String attpath,String shu,Long size,String type,Date uptime,Long attid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateatt"))
    .queryParam("attname",attname)
    .queryParam("attpath",attpath)
    .queryParam("shu",shu)
    .queryParam("size",size)
    .queryParam("type",type)
    .queryParam("uptime",uptime)
    .queryParam("attid",attid)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}