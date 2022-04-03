package cn.gson.oasys.model.entity.attendce;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_attends_list")
public class Attends {

@Id
@Column(name = "attends_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long attendsId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "status_id")
 private  Long statusId;

@Column(name = "attends_time")
 private  Date attendsTime;

@Column(name = "attend_hmtime")
 private  String attendHmtime;

@Column(name = "week_ofday")
 private  String weekOfday;

@Column(name = "attends_ip")
 private  String attendsIp;

@Column(name = "attends_remark")
 private  String attendsRemark;

// 请假开始时间
@Column(name = "holiday_start")
 private  Date holidayStart;

// 请假开始时间
@Column(name = "holiday_days")
 private  Double holidayDays;

@Transient
 private  User user;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Attends() {
    super();
// TODO Auto-generated constructor stub
}public Attends(Long typeId, Long statusId, Date attendsTime, String attendsIp, String attendsRemark, User user) {
    super();
    this.typeId = typeId;
    this.statusId = statusId;
    this.attendsTime = attendsTime;
    this.attendsIp = attendsIp;
    this.attendsRemark = attendsRemark;
    this.user = user;
}public Attends(Long typeId, Long statusId, Date attendsTime, String attendHmtime, String weekOfday, String attendsIp, User user) {
    super();
    this.typeId = typeId;
    this.statusId = statusId;
    this.attendsTime = attendsTime;
    this.attendHmtime = attendHmtime;
    this.weekOfday = weekOfday;
    this.attendsIp = attendsIp;
    this.user = user;
}
public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public String getAttendsIp(){
    return attendsIp;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public Date getHolidayStart(){
    return holidayStart;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Long getTypeId(){
    return typeId;
}


public String getAttendHmtime(){
    return attendHmtime;
}


public Long getStatusId(){
    return statusId;
}


public String getWeekOfday(){
    return weekOfday;
}


public void setWeekOfday(String weekOfday){
    this.weekOfday = weekOfday;
}


public Long getAttendsId(){
    return attendsId;
}


public void setAttendsId(Long attendsId){
    this.attendsId = attendsId;
}


public void setAttendsTime(Date attendsTime){
    this.attendsTime = attendsTime;
}


public Double getHolidayDays(){
    return holidayDays;
}


public void setAttendHmtime(String attendHmtime){
    this.attendHmtime = attendHmtime;
}


public void setHolidayDays(Double holidayDays){
    this.holidayDays = holidayDays;
}


public void setAttendsRemark(String attendsRemark){
    this.attendsRemark = attendsRemark;
}


@Override
public String toString(){
    return "Attends [attendsId=" + attendsId + ", typeId=" + typeId + ", statusId=" + statusId + ", attendsTime=" + attendsTime + ", attendHmtime=" + attendHmtime + ", weekOfday=" + weekOfday + ", attendsIp=" + attendsIp + ", attendsRemark=" + attendsRemark + "]";
}


public String getAttendsRemark(){
    return attendsRemark;
}


public Date getAttendsTime(){
    return attendsTime;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setHolidayStart(Date holidayStart){
    this.holidayStart = holidayStart;
}


public void setAttendsIp(String attendsIp){
    this.attendsIp = attendsIp;
}


}