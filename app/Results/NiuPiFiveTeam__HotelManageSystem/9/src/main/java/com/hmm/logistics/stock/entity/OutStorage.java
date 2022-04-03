package com.hmm.logistics.stock.entity;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.employee.entity.Employee;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
import com.hmm.room.entity.Room;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
import com.hmm.Request.RoomCleanRecordRequest;
import com.hmm.Request.Impl.RoomCleanRecordRequestImpl;
import com.hmm.DTO.RoomCleanRecord;
@Entity
@Table(name = "t_OutStorage")
public class OutStorage {

 private  Long id;

 private  Date outDate;

 private  String reason;

 private  RoomCleanRecord roomCleanRecord;

 private  Employee worker;

 private  List<OutDetailed> outDetailed;

 private  String roomNo;

@Column(name = "emp_id1OFM")
 private Integer emp_id1OFM;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;

@Column(name = "idOU5W")
 private Long idOU5W;

@Transient
 private RoomCleanRecordRequest roomcleanrecordrequest = new RoomCleanRecordRequestImpl();;


public String getReason(){
    return reason;
}


public void setOutDate(Date outDate){
    this.outDate = outDate;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public void setOutDetailed(List<OutDetailed> outDetailed){
    this.outDetailed = outDetailed;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getOutDate(){
    return outDate;
}


@Transient
public Employee getWorker(){
  this.worker = employeerequest.getWorker(this.emp_id1OFM);
return this.worker;
}}



public void setWorker(Employee worker){
this.emp_id1OFM = worker.getWorker() ;
employeerequest.setWorker(worker,this.emp_id1OFM);
 this.worker = worker;
}



public void setRoomCleanRecord(RoomCleanRecord roomCleanRecord){
this.idOU5W = roomCleanRecord.getRoomcleanrecord() ;
roomcleanrecordrequest.setRoomCleanRecord(roomCleanRecord,this.idOU5W);
 this.roomCleanRecord = roomCleanRecord;
}



public void setReason(String reason){
    this.reason = reason;
}


public void setId(Long id){
    this.id = id;
}


@OneToMany(cascade = CascadeType.ALL, mappedBy = "outStorage", fetch = FetchType.LAZY)
public List<OutDetailed> getOutDetailed(){
    return outDetailed;
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
}


@Transient
public RoomCleanRecord getRoomCleanRecord(){
  this.roomCleanRecord = roomcleanrecordrequest.getRoomCleanRecord(this.idOU5W);
return this.roomCleanRecord;
}}



public String getRoomNo(){
    return roomNo;
}


}