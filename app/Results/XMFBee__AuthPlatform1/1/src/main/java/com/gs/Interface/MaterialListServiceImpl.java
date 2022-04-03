package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.MaterialListService;
public class MaterialListServiceImpl implements MaterialListService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void insertList(List<MaterialList> materialLists){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertList"))
    .queryParam("materialLists",materialLists)
;
  restTemplate.put(builder.toUriString(), null);
}


}