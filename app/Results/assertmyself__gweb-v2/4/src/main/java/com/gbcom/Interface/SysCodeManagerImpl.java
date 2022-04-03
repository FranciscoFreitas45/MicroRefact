package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysCodeManager;
public class SysCodeManagerImpl implements SysCodeManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public SysCodeDetail getCodeDetailByCode(String mainCode,String detailCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCodeDetailByCode"))
    .queryParam("mainCode",mainCode)
    .queryParam("detailCode",detailCode)
;  SysCodeDetail aux = restTemplate.getForObject(builder.toUriString(), SysCodeDetail.class);

 return aux;
}


}