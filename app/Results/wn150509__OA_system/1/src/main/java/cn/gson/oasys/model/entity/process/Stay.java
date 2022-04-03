package cn.gson.oasys.model.entity.process;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_stay")
public class Stay {

@Id
@Column(name = "stay_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long stayId;

@Transient
 private  User user;

@Column(name = "stay_time")
 private  Date stayTime;

@Column(name = "leave_time")
 private  Date leaveTime;

@Column(name = "stay_city")
 private  String stayCity;

@Column(name = "hotel_name")
 private  String hotelName;

@Column(name = "day")
 private  Integer day;

@Column(name = "stay_money")
 private  Double stayMoney;

@ManyToOne()
@JoinColumn(name = "evemoney_id")
 private  EvectionMoney evemoney;

@Transient
 private  String nameuser;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public void setLeaveTime(Date leaveTime){
    this.leaveTime = leaveTime;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public String getHotelName(){
    return hotelName;
}


public Double getStayMoney(){
    return stayMoney;
}


public void setStayMoney(Double stayMoney){
    this.stayMoney = stayMoney;
}


public void setStayTime(Date stayTime){
    this.stayTime = stayTime;
}


public void setStayId(Long stayId){
    this.stayId = stayId;
}


public void setNameuser(String nameuser){
    this.nameuser = nameuser;
}


public void setHotelName(String hotelName){
    this.hotelName = hotelName;
}


public Integer getDay(){
    return day;
}


public Long getStayId(){
    return stayId;
}


public String getNameuser(){
    return nameuser;
}


public Date getStayTime(){
    return stayTime;
}


public void setDay(Integer day){
    this.day = day;
}


public void setEvemoney(EvectionMoney evemoney){
    this.evemoney = evemoney;
}


public String getStayCity(){
    return stayCity;
}


@Override
public String toString(){
    return "Stay [stayId=" + stayId + ", stayTime=" + stayTime + ", leaveTime=" + leaveTime + ", stayCity=" + stayCity + ", hotelName=" + hotelName + ", day=" + day + ", stayMoney=" + stayMoney + ", nameuser=" + nameuser + "]";
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public Date getLeaveTime(){
    return leaveTime;
}


public void setStayCity(String stayCity){
    this.stayCity = stayCity;
}


public EvectionMoney getEvemoney(){
    return evemoney;
}


}