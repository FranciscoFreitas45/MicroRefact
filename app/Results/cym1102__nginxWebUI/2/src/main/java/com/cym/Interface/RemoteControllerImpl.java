package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.RemoteController;
public class RemoteControllerImpl implements RemoteController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void fillTree(List<Group> groups,List<Tree> treeList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fillTree"))
    .queryParam("groups",groups)
    .queryParam("treeList",treeList)
;
  restTemplate.put(builder.toUriString(), null);
}


}