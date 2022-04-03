package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.BookService;
public class BookServiceImpl implements BookService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Bookinfo> findBookInfoByBookNameLike(String bookName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookInfoByBookNameLike"))
    .queryParam("bookName",bookName)
;  List<Bookinfo> aux = restTemplate.getForObject(builder.toUriString(), List<Bookinfo>.class);

 return aux;
}


public List<Bookinfo> findBookByISBN(String isbn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookByISBN"))
    .queryParam("isbn",isbn)
;  List<Bookinfo> aux = restTemplate.getForObject(builder.toUriString(), List<Bookinfo>.class);

 return aux;
}


}