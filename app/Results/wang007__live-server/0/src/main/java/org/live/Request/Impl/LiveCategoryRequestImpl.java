package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.LiveCategory;
import org.live.Request.LiveCategoryRequest;
public class LiveCategoryRequestImpl implements LiveCategoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public LiveCategory getCategory(String idHCR5){
 LiveCategory aux = restTemplate.getForObject("http://4/ApplyAnchor/{id}/LiveCategory/getCategory",LiveCategory.class,idHCR5);
return aux;
}


public void setCategory(LiveCategory category,String idHCR5){
 restTemplate.put("http://4/ApplyAnchor/{id}/LiveCategory/setCategory",category,idHCR5);
 return ;
}


}