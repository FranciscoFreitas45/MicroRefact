package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.BucketService;
public class BucketServiceImpl implements BucketService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<GetBucketDTO> findBuckets(String searchTerm,int count,int page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBuckets"))
    .queryParam("searchTerm",searchTerm)
    .queryParam("count",count)
    .queryParam("page",page)
;  List<GetBucketDTO> aux = restTemplate.getForObject(builder.toUriString(), List<GetBucketDTO>.class);

 return aux;
}


}