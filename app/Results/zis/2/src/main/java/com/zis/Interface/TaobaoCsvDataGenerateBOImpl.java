package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.TaobaoCsvDataGenerateBO;
public class TaobaoCsvDataGenerateBOImpl implements TaobaoCsvDataGenerateBO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void generateV2(List<BookInfoAndDetailV2DTO> bookList,String[] emails){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generateV2"))
    .queryParam("bookList",bookList)
    .queryParam("emails",emails)
;
  restTemplate.put(builder.toUriString(), null);
}


}