package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Tag;
import com.ushahidi.swiftriver.core.Request.TagRequest;
public class TagRequestImpl implements TagRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTag(Tag tag,long id3OUD){
 restTemplate.put("http://8/RiverDropTag/{id}/Tag/setTag",tag,id3OUD);
 return ;
}


public Tag getTag(long id3OUD){
 Tag aux = restTemplate.getForObject("http://8/RiverDropTag/{id}/Tag/getTag",Tag.class,id3OUD);
return aux;
}


}