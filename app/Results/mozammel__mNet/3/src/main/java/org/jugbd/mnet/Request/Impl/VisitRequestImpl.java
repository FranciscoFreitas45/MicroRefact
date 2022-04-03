package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Visit;
import org.jugbd.mnet.Request.VisitRequest;
public class VisitRequestImpl implements VisitRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Visit> getVisits(Long id){
 Set<Visit> aux = restTemplate.getForObject("http://2/OutdoorRegister/{id}/Visit/getVisits",Set<Visit>.class,id);
return aux;
}


public OutdoorRegister setVisits(Set<Visit> visits,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://2/OutdoorRegister/{id}/Visit/setVisits?'visits'=visits&'id'=id',",OutdoorRegister.class,id);
return aux;
}


}