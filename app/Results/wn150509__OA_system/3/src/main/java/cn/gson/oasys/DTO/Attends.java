package cn.gson.oasys.DTO;
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
public class Attends {

 private  Long attendsId;

 private  Long typeId;

 private  Long statusId;

 private  Date attendsTime;

 private  String attendHmtime;

 private  String weekOfday;

 private  String attendsIp;

 private  String attendsRemark;

 private  Date holidayStart;

 private  Double holidayDays;

 private  User user;

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
    return user;
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
    this.user = user;
}


public void setHolidayStart(Date holidayStart){
    this.holidayStart = holidayStart;
}


public void setAttendsIp(String attendsIp){
    this.attendsIp = attendsIp;
}


}