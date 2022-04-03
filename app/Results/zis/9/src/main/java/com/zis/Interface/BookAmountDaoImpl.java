package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.BookAmountDao;
public class BookAmountDaoImpl implements BookAmountDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<BookAmount> findByBookIdGradeAndCollege(Integer bookId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBookIdGradeAndCollege"))
    .queryParam("bookId",bookId)
;  List<BookAmount> aux = restTemplate.getForObject(builder.toUriString(), List<BookAmount>.class);

 return aux;
}


public List<BookAmount> findByBookIdListGradeAndCollege(List<Integer> bookId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBookIdListGradeAndCollege"))
    .queryParam("bookId",bookId)
;  List<BookAmount> aux = restTemplate.getForObject(builder.toUriString(), List<BookAmount>.class);

 return aux;
}


}