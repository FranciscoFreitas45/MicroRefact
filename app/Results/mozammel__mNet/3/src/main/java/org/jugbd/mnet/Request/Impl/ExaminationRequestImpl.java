package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Examination;
import org.jugbd.mnet.Request.ExaminationRequest;
public class ExaminationRequestImpl implements ExaminationRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Examination getExamination(Long id){
 Examination aux = restTemplate.getForObject("http://0/OutdoorRegister/{id}/Examination/getExamination",Examination.class,id);
return aux;
}


public OutdoorRegister setExamination(Examination examination,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://0/OutdoorRegister/{id}/Examination/setExamination?'examination'=examination&'id'=id',",OutdoorRegister.class,id);
return aux;
}


}