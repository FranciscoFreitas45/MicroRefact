package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.AuthorJPARepository;
public class AuthorJPARepositoryImpl implements AuthorJPARepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public Optional<Author> findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(String name,String surname){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAuthorByNameIgnoreCaseAndSurnameIgnoreCase"))
    .queryParam("name",name)
    .queryParam("surname",surname)
;  Optional<Author> aux = restTemplate.getForObject(builder.toUriString(), Optional<Author>.class);

 return aux;
}


public List<Author> findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase(String name,String surname){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase"))
    .queryParam("name",name)
    .queryParam("surname",surname)
;  List<Author> aux = restTemplate.getForObject(builder.toUriString(), List<Author>.class);

 return aux;
}


}