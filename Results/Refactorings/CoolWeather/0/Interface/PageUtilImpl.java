import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PageUtilImpl implements PageUtil{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Page<T> listToPage(List<T> list,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("listToPage"))
    .queryParam("list",list)
    .queryParam("pageable",pageable);
  Page<T> aux = restTemplate.getForObject(builder.toUriString(), Page<T>.class)

 return aux;
}


}