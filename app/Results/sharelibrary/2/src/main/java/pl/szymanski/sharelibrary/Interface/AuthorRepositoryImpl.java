package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.AuthorRepository;
public class AuthorRepositoryImpl implements AuthorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Author> findAuthorByNameOrSurname(String name,String surname){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAuthorByNameOrSurname"))
    .queryParam("name",name)
    .queryParam("surname",surname)
;  List<Author> aux = restTemplate.getForObject(builder.toUriString(), List<Author>.class);

 return aux;
}


public Optional<Author> findAuthorByNameAndSurname(String name,String surname){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAuthorByNameAndSurname"))
    .queryParam("name",name)
    .queryParam("surname",surname)
;  Optional<Author> aux = restTemplate.getForObject(builder.toUriString(), Optional<Author>.class);

 return aux;
}


}