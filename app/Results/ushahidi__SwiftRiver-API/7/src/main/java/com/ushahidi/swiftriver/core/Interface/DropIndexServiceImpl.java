package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.DropIndexService;
public class DropIndexServiceImpl implements DropIndexService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<GetDropDTO> findDrops(String searchTerm,int count,int page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDrops"))
    .queryParam("searchTerm",searchTerm)
    .queryParam("count",count)
    .queryParam("page",page)
;  List<GetDropDTO> aux = restTemplate.getForObject(builder.toUriString(), List<GetDropDTO>.class);

 return aux;
}


}