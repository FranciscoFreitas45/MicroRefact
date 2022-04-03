package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.SalaryThresholdService;
public class SalaryThresholdServiceImpl implements SalaryThresholdService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<SalaryThresholdResponse> getSalaryThreshold(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSalaryThreshold"))
;  List<SalaryThresholdResponse> aux = restTemplate.getForObject(builder.toUriString(), List<SalaryThresholdResponse>.class);

 return aux;
}


public void updateThreshold(Long id,SalaryThresholdRequest salaryThresholdRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateThreshold"))
    .queryParam("id",id)
    .queryParam("salaryThresholdRequest",salaryThresholdRequest)
;
  restTemplate.put(builder.toUriString(), null);
}


}