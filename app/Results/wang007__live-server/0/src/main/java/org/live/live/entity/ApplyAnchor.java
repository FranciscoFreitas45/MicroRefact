package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
import org.live.Request.LiveCategoryRequest;
import org.live.Request.Impl.LiveCategoryRequestImpl;
import org.live.DTO.LiveCategory;
@Entity
@Table(name = "live_apply_anchor")
public class ApplyAnchor extends BaseEntity{

 public  int NOT_AUDIT;

 public  int ADUIT_PASS;

 public  int ADUIT_NOT_PASS;

 public  int ADUIT_IGNORE;

@Transient
 private  MobileUser user;

@Transient
 private  LiveCategory category;

@Column
 private  String idCard;

@Column
 private  String realName;

@Column
 private  String idImgUrl;

@Column
 private  int passFlag;

@Column
 private  String noPassReason;

@Column
 private  Date createTime;

@Column(name = "id58IK")
 private String id58IK;

@Transient
 private MobileUserRequest mobileuserrequest = new MobileUserRequestImpl();;

@Column(name = "idHCR5")
 private String idHCR5;

@Transient
 private LiveCategoryRequest livecategoryrequest = new LiveCategoryRequestImpl();;


public void setRealName(String realName){
    this.realName = realName;
}


public void setNoPassReason(String noPassReason){
    this.noPassReason = noPassReason;
}


public Date getCreateTime(){
    return createTime;
}


public String getIdCard(){
    return idCard;
}


public MobileUser getUser(){
  this.user = mobileuserrequest.getUser(this.id58IK);
return this.user;
}}



public LiveCategory getCategory(){
  this.category = livecategoryrequest.getCategory(this.idHCR5);
return this.category;
}}



public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getRealName(){
    return realName;
}


public void setCategory(LiveCategory category){
this.idHCR5 = category.getCategory() ;
livecategoryrequest.setCategory(category,this.idHCR5);
 this.category = category;
}



public void setIdImgUrl(String idImgUrl){
    this.idImgUrl = idImgUrl;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public void setPassFlag(int passFlag){
    this.passFlag = passFlag;
}


public String getIdImgUrl(){
    return idImgUrl;
}


public void setUser(MobileUser user){
this.id58IK = user.getUser() ;
mobileuserrequest.setUser(user,this.id58IK);
 this.user = user;
}



public String getNoPassReason(){
    return noPassReason;
}


public int getPassFlag(){
    return passFlag;
}


}