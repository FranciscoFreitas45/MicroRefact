package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Travel;
import com.hmm.Request.TravelRequest;
public class TravelRequestImpl implements TravelRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Travel> getTravels(Integer emp_id){
 Set<Travel> aux = restTemplate.getForObject("http://6/Employee/{id}/Travel/getTravels",Set<Travel>.class,emp_id);
return aux;
}


public void setTravels(Set<Travel> travels,Integer emp_id){
 restTemplate.put("http://6/Employee/{id}/Travel/setTravels",travels,emp_id);
 return ;
}


}