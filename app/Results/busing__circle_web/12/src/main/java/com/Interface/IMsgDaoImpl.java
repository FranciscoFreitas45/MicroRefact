package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IMsgDao;
public class IMsgDaoImpl implements IMsgDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<MessageTypeBean> queryMessageTypeBeans(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryMessageTypeBeans"))
;  List<MessageTypeBean> aux = restTemplate.getForObject(builder.toUriString(), List<MessageTypeBean>.class);

 return aux;
}


}