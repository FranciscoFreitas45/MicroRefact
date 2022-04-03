package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Tag;
import com.ushahidi.swiftriver.core.Request.TagRequest;
public class TagRequestImpl implements TagRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTag(Tag tag,long idQ98E){
 restTemplate.put("http://8/BucketDropTag/{id}/Tag/setTag",tag,idQ98E);
 return ;
}


public Tag getTag(long idQ98E){
 Tag aux = restTemplate.getForObject("http://8/BucketDropTag/{id}/Tag/getTag",Tag.class,idQ98E);
return aux;
}


}