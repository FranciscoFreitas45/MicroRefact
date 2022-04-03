package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.SectorRepository;
public class SectorRepositoryImpl implements SectorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public List<Object[]> findByIC_Type(String IC_Type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIC_Type"))
    .queryParam("IC_Type",IC_Type)
;  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class);

 return aux;
}


}