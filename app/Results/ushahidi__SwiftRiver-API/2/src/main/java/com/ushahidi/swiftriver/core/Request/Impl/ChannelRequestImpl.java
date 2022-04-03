package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Channel;
import com.ushahidi.swiftriver.core.Request.ChannelRequest;
public class ChannelRequestImpl implements ChannelRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setChannels(List<Channel> channels,Long id){
 restTemplate.put("http://1/River/{id}/Channel/setChannels",channels,id);
 return ;
}


public List<Channel> getChannels(Long id){
 List<Channel> aux = restTemplate.getForObject("http://1/River/{id}/Channel/getChannels",List<Channel>.class,id);
return aux;
}


}