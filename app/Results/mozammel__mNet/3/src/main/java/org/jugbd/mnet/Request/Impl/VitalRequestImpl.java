package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Vital;
import org.jugbd.mnet.Request.VitalRequest;
public class VitalRequestImpl implements VitalRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public OutdoorRegister setVitals(Set<Vital> vitals,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://8/OutdoorRegister/{id}/Vital/setVitals?'vitals'=vitals&'id'=id',",OutdoorRegister.class,id);
return aux;
}


public Set<Vital> getVitals(Long id){
 Set<Vital> aux = restTemplate.getForObject("http://8/OutdoorRegister/{id}/Vital/getVitals",Set<Vital>.class,id);
return aux;
}


}