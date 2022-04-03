package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OperationalDetail;
import org.jugbd.mnet.Request.OperationalDetailRequest;
public class OperationalDetailRequestImpl implements OperationalDetailRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<OperationalDetail> getOperationalDetails(Long id){
 Set<OperationalDetail> aux = restTemplate.getForObject("http://13/Register/{id}/OperationalDetail/getOperationalDetails",Set<OperationalDetail>.class,id);
return aux;
}


public void setOperationalDetails(Set<OperationalDetail> operationalDetails,Long id){
 restTemplate.put("http://13/Register/{id}/OperationalDetail/setOperationalDetails",operationalDetails,id);
 return ;
}


}