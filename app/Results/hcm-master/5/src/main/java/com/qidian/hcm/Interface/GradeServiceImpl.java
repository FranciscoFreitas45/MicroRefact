package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.GradeService;
public class GradeServiceImpl implements GradeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Map<Long,String> getIdNameMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdNameMap"))
;  Map<Long,String> aux = restTemplate.getForObject(builder.toUriString(), Map<Long,String>.class);

 return aux;
}


}