package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.BookService;
public class BookServiceImpl implements BookService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Bookinfo> findBySpecificationAll(Specification<Bookinfo> spec){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySpecificationAll"))
    .queryParam("spec",spec)
;  List<Bookinfo> aux = restTemplate.getForObject(builder.toUriString(), List<Bookinfo>.class);

 return aux;
}


public Page<Bookinfo> findAll(Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("pageable",pageable)
;  Page<Bookinfo> aux = restTemplate.getForObject(builder.toUriString(), Page<Bookinfo>.class);

 return aux;
}


}