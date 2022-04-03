package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Media;
import com.ushahidi.swiftriver.core.Request.MediaRequest;
public class MediaRequestImpl implements MediaRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Media getImage(long idBTYE){
 Media aux = restTemplate.getForObject("http://3/Drop/{id}/Media/getImage",Media.class,idBTYE);
return aux;
}


public void setImage(Media image,long idBTYE){
 restTemplate.put("http://3/Drop/{id}/Media/setImage",image,idBTYE);
 return ;
}


}