package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.BookAmountDao;
public class BookAmountDaoImpl implements BookAmountDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Integer> distinctBookId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/distinctBookId"))
;  List<Integer> aux = restTemplate.getForObject(builder.toUriString(), List<Integer>.class);

 return aux;
}


}