package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.Request.DropRequest;
public class DropRequestImpl implements DropRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDrop(Drop drop,long id2DXZ){
 restTemplate.put("http://6/RiverDrop/{id}/Drop/setDrop",drop,id2DXZ);
 return ;
}


public Drop getDrop(long id2DXZ){
 Drop aux = restTemplate.getForObject("http://6/RiverDrop/{id}/Drop/getDrop",Drop.class,id2DXZ);
return aux;
}


}