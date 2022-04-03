package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.BookJPARepository;
public class BookJPARepositoryImpl implements BookJPARepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Book> findByTitleIsContainingIgnoreCase(String title){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTitleIsContainingIgnoreCase"))
    .queryParam("title",title)
;  List<Book> aux = restTemplate.getForObject(builder.toUriString(), List<Book>.class);

 return aux;
}


public List<Book> findByUserId(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserId"))
    .queryParam("userId",userId)
;  List<Book> aux = restTemplate.getForObject(builder.toUriString(), List<Book>.class);

 return aux;
}


}