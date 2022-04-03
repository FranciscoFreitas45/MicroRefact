package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OutdoorRegister;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
public class OutdoorRegisterRequestImpl implements OutdoorRegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public TreatmentPlan setOutdoorRegister(OutdoorRegister outdoorRegister,Long id){
 TreatmentPlan aux = restTemplate.getForObject("http://3/TreatmentPlan/{id}/OutdoorRegister/setOutdoorRegister?'outdoorRegister'=outdoorRegister&'id'=id',",TreatmentPlan.class,id);
return aux;
}


public OutdoorRegister getOutdoorRegister(Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://3/TreatmentPlan/{id}/OutdoorRegister/getOutdoorRegister",OutdoorRegister.class,id);
return aux;
}


}