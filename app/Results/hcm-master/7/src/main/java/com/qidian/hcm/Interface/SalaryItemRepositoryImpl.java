package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.SalaryItemRepository;
public class SalaryItemRepositoryImpl implements SalaryItemRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Optional<SalaryItem> findByCode(String code){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCode"))
    .queryParam("code",code)
;  Optional<SalaryItem> aux = restTemplate.getForObject(builder.toUriString(), Optional<SalaryItem>.class);

 return aux;
}


}