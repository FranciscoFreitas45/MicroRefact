package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.MedicalHistory;
import org.jugbd.mnet.Request.MedicalHistoryRequest;
public class MedicalHistoryRequestImpl implements MedicalHistoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public MedicalHistory getMedicalHistory(Long id){
 MedicalHistory aux = restTemplate.getForObject("http://7/Register/{id}/MedicalHistory/getMedicalHistory",MedicalHistory.class,id);
return aux;
}


public void setMedicalHistory(MedicalHistory medicalHistory,Long id){
 restTemplate.put("http://7/Register/{id}/MedicalHistory/setMedicalHistory",medicalHistory,id);
 return ;
}


}