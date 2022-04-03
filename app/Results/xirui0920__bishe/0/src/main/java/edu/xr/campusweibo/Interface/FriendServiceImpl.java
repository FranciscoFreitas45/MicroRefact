package edu.xr.campusweibo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.Interface.FriendService;
public class FriendServiceImpl implements FriendService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Friend> getAllFrid(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllFrid"))
    .queryParam("id",id)
;  List<Friend> aux = restTemplate.getForObject(builder.toUriString(), List<Friend>.class);

 return aux;
}


public List<Friend> getAllFans(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllFans"))
    .queryParam("id",id)
;  List<Friend> aux = restTemplate.getForObject(builder.toUriString(), List<Friend>.class);

 return aux;
}


}