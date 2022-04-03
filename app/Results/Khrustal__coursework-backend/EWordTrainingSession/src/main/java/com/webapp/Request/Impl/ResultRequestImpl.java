package com.webapp.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.DTO.Result;
import com.webapp.Request.ResultRequest;
public class ResultRequestImpl implements ResultRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Result> getResults(Long id){
 List<Result> aux = restTemplate.getForObject("http://0/TrainingSession/{id}/Result/getResults",List<Result>.class,id);
return aux;
}


public void setResults(List<Result> results,Long id){
 restTemplate.put("http://0/TrainingSession/{id}/Result/setResults",results,id);
 return ;
}


}