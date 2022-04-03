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
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
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

 private Long userId;

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


public Long getAttendsId(){
    return attendsId;
}


public Double getHolidayDays(){
    return holidayDays;
}


public String getAttendsRemark(){
    return attendsRemark;
}


public Date getAttendsTime(){
    return attendsTime;
}


}