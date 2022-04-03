package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.TrackListService;
public class TrackListServiceImpl implements TrackListService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object queryByPager(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPager"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int countName(TrackList trackList,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countName"))
    .queryParam("trackList",trackList)
    .queryParam("user",user)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<TrackList> queryByPagerName(Pager pager,TrackList trackList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPagerName"))
    .queryParam("pager",pager)
    .queryParam("trackList",trackList)
;  List<TrackList> aux = restTemplate.getForObject(builder.toUriString(), List<TrackList>.class);

 return aux;
}


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}