package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.BookService;
public class BookServiceImpl implements BookService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Book findBookById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookById"))
    .queryParam("id",id)
;  Book aux = restTemplate.getForObject(builder.toUriString(), Book.class);

 return aux;
}


}