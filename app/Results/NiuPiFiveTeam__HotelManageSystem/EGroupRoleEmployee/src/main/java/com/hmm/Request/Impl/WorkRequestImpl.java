package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Work;
import com.hmm.Request.WorkRequest;
public class WorkRequestImpl implements WorkRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Work> getWorks(Integer emp_id){
 Set<Work> aux = restTemplate.getForObject("http://7/Employee/{id}/Work/getWorks",Set<Work>.class,emp_id);
return aux;
}


public void setWorks(Set<Work> works,Integer emp_id){
 restTemplate.put("http://7/Employee/{id}/Work/setWorks",works,emp_id);
 return ;
}


}