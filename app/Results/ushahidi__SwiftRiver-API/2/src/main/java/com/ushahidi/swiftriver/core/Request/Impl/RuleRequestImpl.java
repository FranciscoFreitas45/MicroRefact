package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Rule;
import com.ushahidi.swiftriver.core.Request.RuleRequest;
public class RuleRequestImpl implements RuleRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Rule> getRules(Long id){
 List<Rule> aux = restTemplate.getForObject("http://0/River/{id}/Rule/getRules",List<Rule>.class,id);
return aux;
}


public void setRules(List<Rule> rules,Long id){
 restTemplate.put("http://0/River/{id}/Rule/setRules",rules,id);
 return ;
}


}