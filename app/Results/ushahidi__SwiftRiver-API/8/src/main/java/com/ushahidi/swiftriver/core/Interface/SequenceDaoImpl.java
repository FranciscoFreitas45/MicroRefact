package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.SequenceDao;
public class SequenceDaoImpl implements SequenceDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public long getIds(Sequence seq,int num){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIds"))
    .queryParam("seq",seq)
    .queryParam("num",num)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}