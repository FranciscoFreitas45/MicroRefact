package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.RoomCleanRecord;
import com.hmm.Request.RoomCleanRecordRequest;
public class RoomCleanRecordRequestImpl implements RoomCleanRecordRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setRoomCleanRecord(RoomCleanRecord roomCleanRecord,Long idOU5W){
 restTemplate.put("http://10/OutStorage/{id}/RoomCleanRecord/setRoomCleanRecord",roomCleanRecord,idOU5W);
 return ;
}


public RoomCleanRecord getRoomCleanRecord(Long idOU5W){
 RoomCleanRecord aux = restTemplate.getForObject("http://10/OutStorage/{id}/RoomCleanRecord/getRoomCleanRecord",RoomCleanRecord.class,idOU5W);
return aux;
}


}