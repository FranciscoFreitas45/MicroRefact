package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.BookService;
public class BookServiceImpl implements BookService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Bookinfo findBookById(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookById"))
    .queryParam("id",id)
;  Bookinfo aux = restTemplate.getForObject(builder.toUriString(), Bookinfo.class);

 return aux;
}


}