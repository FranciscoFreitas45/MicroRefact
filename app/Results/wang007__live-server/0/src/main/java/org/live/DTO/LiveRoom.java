package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import org.live.Request.AnchorRequest;
import org.live.Request.Impl.AnchorRequestImpl;
import org.live.DTO.Anchor;
import org.live.Request.LiveCategoryRequest;
import org.live.Request.Impl.LiveCategoryRequestImpl;
import org.live.DTO.LiveCategory;
public class LiveRoom extends BaseEntity{

 private  Anchor anchor;

 private  LiveCategory liveCategory;

 private  String roomNum;

 private  String roomName;

 private  String coverUrl;

 private  String liveRoomUrl;

 private  boolean liveFlag;

 private  String roomLabel;

 private  boolean banLiveFlag;

 private  int onlineCount;

 private  long historyMaxOnlineCount;

 private String idFK1I;

 private String idW6JM;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public LiveCategory getLiveCategory(){
    return liveCategory;
}


public String getCoverUrl(){
    return coverUrl;
}


public Anchor getAnchor(){
    return anchor;
}


public int getOnlineCount(){
    return onlineCount;
}


public long getHistoryMaxOnlineCount(){
    return historyMaxOnlineCount;
}


public String getLiveRoomUrl(){
    return liveRoomUrl;
}


public String getRoomNum(){
    return roomNum;
}


public String getRoomLabel(){
    return roomLabel;
}


public String getRoomName(){
    return roomName;
}


public void setAnchor(Anchor anchor){
this.idFK1I = anchor.getAnchor() ;
anchorrequest.setAnchor(anchor,this.idFK1I);
 this.anchor = anchor;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAnchor"))

.queryParam("anchor",anchor)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLiveCategory(LiveCategory liveCategory){
this.idW6JM = liveCategory.getLivecategory() ;
livecategoryrequest.setLiveCategory(liveCategory,this.idW6JM);
 this.liveCategory = liveCategory;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLiveCategory"))

.queryParam("liveCategory",liveCategory)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoomNum"))

.queryParam("roomNum",roomNum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoomLabel(String roomLabel){
    this.roomLabel = roomLabel;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoomLabel"))

.queryParam("roomLabel",roomLabel)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLiveRoomUrl(String liveRoomUrl){
    this.liveRoomUrl = liveRoomUrl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLiveRoomUrl"))

.queryParam("liveRoomUrl",liveRoomUrl)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCoverUrl(String coverUrl){
    this.coverUrl = coverUrl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCoverUrl"))

.queryParam("coverUrl",coverUrl)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoomName(String roomName){
    this.roomName = roomName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoomName"))

.queryParam("roomName",roomName)
;
restTemplate.put(builder.toUriString(),null);
}


}