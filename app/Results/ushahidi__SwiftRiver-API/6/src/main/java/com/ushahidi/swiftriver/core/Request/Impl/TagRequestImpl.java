package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Tag;
import com.ushahidi.swiftriver.core.Request.TagRequest;
public class TagRequestImpl implements TagRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Tag> getTags(long id){
 List<Tag> aux = restTemplate.getForObject("http://8/Drop/{id}/Tag/getTags",List<Tag>.class,id);
return aux;
}


public void setTags(List<Tag> tags,long id){
 restTemplate.put("http://8/Drop/{id}/Tag/setTags",tags,id);
 return ;
}


}