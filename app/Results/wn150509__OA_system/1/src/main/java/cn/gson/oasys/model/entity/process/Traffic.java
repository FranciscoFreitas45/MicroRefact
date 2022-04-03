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
@Table(name = "aoa_traffic")
public class Traffic {

@Id
@Column(name = "traffic_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long trafficId;

@Transient
 private  User user;

@Column(name = "depart_time")
 private  Date departTime;

@Column(name = "depart_name")
 private  String departName;

@Column(name = "reach_name")
 private  String reachName;

@Column(name = "traffic_name")
 private  String trafficName;

@Column(name = "seat_type")
 private  String seatType;

@Column(name = "traffic_money")
 private  Double trafficMoney;

@ManyToOne()
@JoinColumn(name = "evection_id")
 private  EvectionMoney evection;

@Transient
 private  String username;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public String getReachName(){
    return reachName;
}


public void setReachName(String reachName){
    this.reachName = reachName;
}


public String getTrafficName(){
    return trafficName;
}


public void setUsername(String username){
    this.username = username;
}


public void setDepartTime(Date departTime){
    this.departTime = departTime;
}


public void setDepartName(String departName){
    this.departName = departName;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public EvectionMoney getEvection(){
    return evection;
}


public Long getTrafficId(){
    return trafficId;
}


public void setEvection(EvectionMoney evection){
    this.evection = evection;
}


public String getUsername(){
    return username;
}


public void setTrafficName(String trafficName){
    this.trafficName = trafficName;
}


public Double getTrafficMoney(){
    return trafficMoney;
}


public String getSeatType(){
    return seatType;
}


public void setTrafficId(Long trafficId){
    this.trafficId = trafficId;
}


public Date getDepartTime(){
    return departTime;
}


public void setTrafficMoney(Double trafficMoney){
    this.trafficMoney = trafficMoney;
}


public String getDepartName(){
    return departName;
}


@Override
public String toString(){
    return "Traffic [trafficId=" + trafficId + ", departTime=" + departTime + ", departName=" + departName + ", reachName=" + reachName + ", trafficName=" + trafficName + ", seatType=" + seatType + ", evection=" + evection + ", username=" + username + "]";
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setSeatType(String seatType){
    this.seatType = seatType;
}


}