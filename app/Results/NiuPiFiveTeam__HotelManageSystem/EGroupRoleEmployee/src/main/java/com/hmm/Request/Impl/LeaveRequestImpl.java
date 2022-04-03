package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Leave;
import com.hmm.Request.LeaveRequest;
public class LeaveRequestImpl implements LeaveRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setLeaves(Set<Leave> leaves,Integer emp_id){
 restTemplate.put("http://2/Employee/{id}/Leave/setLeaves",leaves,emp_id);
 return ;
}


public Set<Leave> getLeaves(Integer emp_id){
 Set<Leave> aux = restTemplate.getForObject("http://2/Employee/{id}/Leave/getLeaves",Set<Leave>.class,emp_id);
return aux;
}


}