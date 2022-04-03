package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.CalculateService;
public class CalculateServiceImpl implements CalculateService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void formulaCalculate(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/formulaCalculate"))
;
  restTemplate.put(builder.toUriString(), null);
}


}