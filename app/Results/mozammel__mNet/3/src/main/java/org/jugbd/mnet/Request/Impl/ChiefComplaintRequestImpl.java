package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.ChiefComplaint;
import org.jugbd.mnet.Request.ChiefComplaintRequest;
public class ChiefComplaintRequestImpl implements ChiefComplaintRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public OutdoorRegister setChiefComplaint(ChiefComplaint chiefComplaint,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://10/OutdoorRegister/{id}/ChiefComplaint/setChiefComplaint?'chiefComplaint'=chiefComplaint&'id'=id',",OutdoorRegister.class,id);
return aux;
}


public ChiefComplaint getChiefComplaint(Long id){
 ChiefComplaint aux = restTemplate.getForObject("http://10/OutdoorRegister/{id}/ChiefComplaint/getChiefComplaint",ChiefComplaint.class,id);
return aux;
}


}