package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.CounterCountRepository;
public class CounterCountRepositoryImpl implements CounterCountRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public CounterCount findTotalCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTotalCount"))
;  CounterCount aux = restTemplate.getForObject(builder.toUriString(), CounterCount.class);

 return aux;
}


public void save(CounterCount counterCount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("counterCount",counterCount)
;
  restTemplate.put(builder.toUriString(), null);
}


}