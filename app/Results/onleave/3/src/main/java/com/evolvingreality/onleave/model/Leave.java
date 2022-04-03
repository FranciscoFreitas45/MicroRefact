package com.evolvingreality.onleave.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import com.evolvingreality.onleave.Request.UserRequest;
import com.evolvingreality.onleave.Request.Impl.UserRequestImpl;
import com.evolvingreality.onleave.DTO.User;
@Entity
@Table(name = "LEAVE")
public class Leave extends AbstractAuditingEntity{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
@Column(name = "start_date_time")
 private  DateTime startDateTime;

@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
@Column(name = "end_date_time")
 private  DateTime endDateTime;

@Enumerated(EnumType.STRING)
@Column(name = "leave_type")
 private  LeaveType leaveType;

@Enumerated(EnumType.STRING)
@Column(name = "leave_status")
 private  LeaveStatus leaveStatus;

@Transient
 private  User user;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public DateTime getEndDateTime(){
    return endDateTime;
}


public void setEndDateTime(DateTime endDateTime){
    this.endDateTime = endDateTime;
}


public void setLeaveStatus(LeaveStatus leaveStatus){
    this.leaveStatus = leaveStatus;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public void setId(Long id){
    this.id = id;
}


public DateTime getStartDateTime(){
    return startDateTime;
}


public LeaveType getLeaveType(){
    return leaveType;
}


public void setLeaveType(LeaveType leaveType){
    this.leaveType = leaveType;
}


public Long getId(){
    return id;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public void setStartDateTime(DateTime startDateTime){
    this.startDateTime = startDateTime;
}


public LeaveStatus getLeaveStatus(){
    return leaveStatus;
}


}