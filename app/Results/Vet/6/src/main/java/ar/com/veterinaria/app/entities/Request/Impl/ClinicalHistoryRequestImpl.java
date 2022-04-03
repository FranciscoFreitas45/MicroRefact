package ar.com.veterinaria.app.entities.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.DTO.ClinicalHistory;
import ar.com.veterinaria.app.entities.Request.ClinicalHistoryRequest;
public class ClinicalHistoryRequestImpl implements ClinicalHistoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setClinicalHistory(ClinicalHistory clinicalHistory,Integer id){
 restTemplate.put("http://5/Pet/{id}/ClinicalHistory/setClinicalHistory",clinicalHistory,id);
 return ;
}


public ClinicalHistory getClinicalHistory(Integer id){
 ClinicalHistory aux = restTemplate.getForObject("http://5/Pet/{id}/ClinicalHistory/getClinicalHistory",ClinicalHistory.class,id);
return aux;
}


}