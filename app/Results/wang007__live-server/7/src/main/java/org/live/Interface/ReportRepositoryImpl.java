package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.ReportRepository;
public class ReportRepositoryImpl implements ReportRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Report getRecentlyReport(String userId,String liveRoomId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRecentlyReport"))
    .queryParam("userId",userId)
    .queryParam("liveRoomId",liveRoomId)
;  Report aux = restTemplate.getForObject(builder.toUriString(), Report.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}