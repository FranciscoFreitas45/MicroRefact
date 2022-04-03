package br.com.wtag.lottery.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.wtag.lottery.Interface.BetsRepository;
public class BetsRepositoryImpl implements BetsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Bets> findByEmailOrderByRegisteredAsc(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmailOrderByRegisteredAsc"))
    .queryParam("email",email)
;  List<Bets> aux = restTemplate.getForObject(builder.toUriString(), List<Bets>.class);

 return aux;
}


}