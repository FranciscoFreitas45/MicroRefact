package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.RiverCollaborator;
import com.ushahidi.swiftriver.core.Request.RiverCollaboratorRequest;
public class RiverCollaboratorRequestImpl implements RiverCollaboratorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public RiverCollaborator getActionOnObj(Long idD9W3){
 RiverCollaborator aux = restTemplate.getForObject("http://2/RiverCollaboratorActivity/{id}/RiverCollaborator/getActionOnObj",RiverCollaborator.class,idD9W3);
return aux;
}


public void setActionOnObj(RiverCollaborator actionOnObj,Long idD9W3){
 restTemplate.put("http://2/RiverCollaboratorActivity/{id}/RiverCollaborator/setActionOnObj",actionOnObj,idD9W3);
 return ;
}


}