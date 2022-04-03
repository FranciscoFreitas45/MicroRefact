package net.shangtech.weixin.appointment.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "appointment_history")
public class AppointmentHistory extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "appointment_id")
 private  Integer appointmentId;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "real_name")
 private  String realName;

@Column(name = "tel")
 private  String tel;


public void setRealName(String realName){
    this.realName = realName;
}


public Date getCreateTime(){
    return createTime;
}


public Integer getAppointmentId(){
    return appointmentId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setAppointmentId(Integer appointmentId){
    this.appointmentId = appointmentId;
}


public void setTel(String tel){
    this.tel = tel;
}


public String getRealName(){
    return realName;
}


public String getTel(){
    return tel;
}


}