package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Bcard;
import com.hmm.Request.BcardRequest;
public class BcardRequestImpl implements BcardRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Bcard> getBcards(Integer emp_id){
 Set<Bcard> aux = restTemplate.getForObject("http://1/Employee/{id}/Bcard/getBcards",Set<Bcard>.class,emp_id);
return aux;
}


public void setBcards(Set<Bcard> bcards,Integer emp_id){
 restTemplate.put("http://1/Employee/{id}/Bcard/setBcards",bcards,emp_id);
 return ;
}


}