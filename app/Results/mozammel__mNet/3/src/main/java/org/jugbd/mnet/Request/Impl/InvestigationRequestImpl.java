package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Investigation;
import org.jugbd.mnet.Request.InvestigationRequest;
public class InvestigationRequestImpl implements InvestigationRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Investigation> getInvestigation(Long id){
 Set<Investigation> aux = restTemplate.getForObject("http://11/Register/{id}/Investigation/getInvestigation",Set<Investigation>.class,id);
return aux;
}


public void setInvestigation(Set<Investigation> investigation,Long id){
 restTemplate.put("http://11/Register/{id}/Investigation/setInvestigation",investigation,id);
 return ;
}


}