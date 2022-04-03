package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import org.live.Request.AnchorRequest;
import org.live.Request.Impl.AnchorRequestImpl;
import org.live.DTO.Anchor;
import org.live.Request.LiveCategoryRequest;
import org.live.Request.Impl.LiveCategoryRequestImpl;
import org.live.DTO.LiveCategory;
@Entity
@Table(name = "live_liveroom")
public class LiveRoom extends BaseEntity{

@Transient
 private  Anchor anchor;

@Transient
 private  LiveCategory liveCategory;

@Column
 private  String roomNum;

@Column
 private  String roomName;

@Column
 private  String coverUrl;

@Column
 private  String liveRoomUrl;

@Column
 private  boolean liveFlag;

@Column
 private  String roomLabel;

@Column
 private  boolean banLiveFlag;

@Column
 private  int onlineCount;

@Column
 private  long historyMaxOnlineCount;

@Column(name = "idFK1I")
 private String idFK1I;

@Transient
 private AnchorRequest anchorrequest = new AnchorRequestImpl();;

@Column(name = "idW6JM")
 private String idW6JM;

@Transient
 private LiveCategoryRequest livecategoryrequest = new LiveCategoryRequestImpl();;


public void setCoverUrl(String coverUrl){
    this.coverUrl = coverUrl;
}


public void setOnlineCount(int onlineCount){
    this.onlineCount = onlineCount;
}


public LiveCategory getLiveCategory(){
  this.liveCategory = livecategoryrequest.getLiveCategory(this.idW6JM);
return this.liveCategory;
}}



public void setHistoryMaxOnlineCount(long historyMaxOnlineCount){
    this.historyMaxOnlineCount = historyMaxOnlineCount;
}


public String getCoverUrl(){
    return coverUrl;
}


public boolean isBanLiveFlag(){
    return banLiveFlag;
}


public Anchor getAnchor(){
  this.anchor = anchorrequest.getAnchor(this.idFK1I);
return this.anchor;
}}



public void setBanLiveFlag(boolean banLiveFlag){
    this.banLiveFlag = banLiveFlag;
}


public void setRoomName(String roomName){
    this.roomName = roomName;
}


public boolean isLiveFlag(){
    return liveFlag;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public int getOnlineCount(){
    return onlineCount;
}


public void setLiveFlag(boolean liveFlag){
    this.liveFlag = liveFlag;
}


public void setLiveRoomUrl(String liveRoomUrl){
    this.liveRoomUrl = liveRoomUrl;
}


public void setAnchor(Anchor anchor){
this.idFK1I = anchor.getAnchor() ;
anchorrequest.setAnchor(anchor,this.idFK1I);
 this.anchor = anchor;
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


public void setRoomLabel(String roomLabel){
    this.roomLabel = roomLabel;
}


public void setLiveCategory(LiveCategory liveCategory){
this.idW6JM = liveCategory.getLivecategory() ;
livecategoryrequest.setLiveCategory(liveCategory,this.idW6JM);
 this.liveCategory = liveCategory;
}



public String getRoomName(){
    return roomName;
}


}