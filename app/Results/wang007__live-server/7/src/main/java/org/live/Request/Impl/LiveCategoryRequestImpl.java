package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.LiveCategory;
import org.live.Request.LiveCategoryRequest;
public class LiveCategoryRequestImpl implements LiveCategoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public LiveCategory getLiveCategory(String idW6JM){
 LiveCategory aux = restTemplate.getForObject("http://4/LiveRoom/{id}/LiveCategory/getLiveCategory",LiveCategory.class,idW6JM);
return aux;
}


public void setLiveCategory(LiveCategory liveCategory,String idW6JM){
 restTemplate.put("http://4/LiveRoom/{id}/LiveCategory/setLiveCategory",liveCategory,idW6JM);
 return ;
}


}