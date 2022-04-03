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


public LiveCategory getLiveCategory(){
  this.liveCategory = livecategoryrequest.getLiveCategory(this.idW6JM);
return this.liveCategory;
}}



public String getCoverUrl(){
    return coverUrl;
}


public Anchor getAnchor(){
  this.anchor = anchorrequest.getAnchor(this.idFK1I);
return this.anchor;
}}



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


}