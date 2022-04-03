package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.IndicatorRepository;
public class IndicatorRepositoryImpl implements IndicatorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public UtIndicatorEn findByIndicator_NId(int indicator_NId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIndicator_NId"))
    .queryParam("indicator_NId",indicator_NId)
;  UtIndicatorEn aux = restTemplate.getForObject(builder.toUriString(), UtIndicatorEn.class);

 return aux;
}


}