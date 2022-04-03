package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.Request.DropRequest;
public class DropRequestImpl implements DropRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDrop(Drop drop,long idINT8){
 restTemplate.put("http://6/BucketDrop/{id}/Drop/setDrop",drop,idINT8);
 return ;
}


public Drop getDrop(long idINT8){
 Drop aux = restTemplate.getForObject("http://6/BucketDrop/{id}/Drop/getDrop",Drop.class,idINT8);
return aux;
}


}