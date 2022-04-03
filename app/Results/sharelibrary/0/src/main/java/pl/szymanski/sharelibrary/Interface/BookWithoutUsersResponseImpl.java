package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.BookWithoutUsersResponse;
public class BookWithoutUsersResponseImpl implements BookWithoutUsersResponse{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public BookWithoutUsersResponse of(Book book){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("book",book)
;  BookWithoutUsersResponse aux = restTemplate.getForObject(builder.toUriString(), BookWithoutUsersResponse.class);

 return aux;
}


}