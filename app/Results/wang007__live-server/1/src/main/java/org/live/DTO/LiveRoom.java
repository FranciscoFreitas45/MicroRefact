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


public boolean isBanLiveFlag(){
    return banLiveFlag;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/isBanLiveFlag"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}