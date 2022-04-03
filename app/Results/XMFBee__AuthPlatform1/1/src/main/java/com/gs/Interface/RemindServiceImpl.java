package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.RemindService;
public class RemindServiceImpl implements RemindService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://19";


public List<Remind> selectRemind(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/selectRemind"))
;  List<Remind> aux = restTemplate.getForObject(builder.toUriString(), List<Remind>.class);

 return aux;
}


}