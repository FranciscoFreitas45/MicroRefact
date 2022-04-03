package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.RiverService;
public class RiverServiceImpl implements RiverService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<GetRiverDTO> findRivers(String searchTerm,int count,int page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRivers"))
    .queryParam("searchTerm",searchTerm)
    .queryParam("count",count)
    .queryParam("page",page)
;  List<GetRiverDTO> aux = restTemplate.getForObject(builder.toUriString(), List<GetRiverDTO>.class);

 return aux;
}


}