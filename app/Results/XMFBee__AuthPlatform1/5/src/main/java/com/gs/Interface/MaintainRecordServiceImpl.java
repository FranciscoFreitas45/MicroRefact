package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.MaintainRecordService;
public class MaintainRecordServiceImpl implements MaintainRecordService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void updateActualEndTime(String maintainRecordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateActualEndTime"))
    .queryParam("maintainRecordId",maintainRecordId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateCurrentStatus(String currentStatus,String recordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateCurrentStatus"))
    .queryParam("currentStatus",currentStatus)
    .queryParam("recordId",recordId)
;
  restTemplate.put(builder.toUriString(), null);
}


}