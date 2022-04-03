package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.TreatmentPlan;
import org.jugbd.mnet.Request.TreatmentPlanRequest;
public class TreatmentPlanRequestImpl implements TreatmentPlanRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public OutdoorRegister setTreatmentPlan(TreatmentPlan treatmentPlan,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://1/OutdoorRegister/{id}/TreatmentPlan/setTreatmentPlan?'treatmentPlan'=treatmentPlan&'id'=id',",OutdoorRegister.class,id);
return aux;
}


public TreatmentPlan getTreatmentPlan(Long id){
 TreatmentPlan aux = restTemplate.getForObject("http://1/OutdoorRegister/{id}/TreatmentPlan/getTreatmentPlan",TreatmentPlan.class,id);
return aux;
}


}