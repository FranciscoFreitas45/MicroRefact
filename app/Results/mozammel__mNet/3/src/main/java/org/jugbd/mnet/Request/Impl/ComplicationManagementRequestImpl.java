package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.ComplicationManagement;
import org.jugbd.mnet.Request.ComplicationManagementRequest;
public class ComplicationManagementRequestImpl implements ComplicationManagementRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ComplicationManagement getComplicationManagement(Long id){
 ComplicationManagement aux = restTemplate.getForObject("http://14/Register/{id}/ComplicationManagement/getComplicationManagement",ComplicationManagement.class,id);
return aux;
}


public void setComplicationManagement(ComplicationManagement complicationManagement,Long id){
 restTemplate.put("http://14/Register/{id}/ComplicationManagement/setComplicationManagement",complicationManagement,id);
 return ;
}


}