package com.hmm.logistics.roomClean.entity;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.employee.entity.Employee;
import com.hmm.logistics.stock.entity.OutDetailed;
import com.hmm.logistics.stock.entity.OutStorage;
import com.hmm.room.entity.Room;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
@Entity
@Table(name = "t_roomcleanRecord")
public class RoomCleanRecord {

 private  Long id;

 private  String roomHandle;

 private  String roomOther;

 private  Date roomDate;

 private  Employee roomWorker;

 private  OutStorage outStorage;

 private  Room room;

@Column(name = "emp_idU6JN")
 private Integer emp_idU6JN;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRoomDate(){
    return roomDate;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public String getRoomHandle(){
    return roomHandle;
}


public void setRoomWorker(Employee roomWorker){
this.emp_idU6JN = roomWorker.getRoomworker() ;
employeerequest.setRoomWorker(roomWorker,this.emp_idU6JN);
 this.roomWorker = roomWorker;
}



public void setRoom(Room room){
    this.room = room;
}


public String getRoomOther(){
    return roomOther;
}


// mappedBy="roomCleanRecord"
@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
public OutStorage getOutStorage(){
    return outStorage;
}


public void setRoomHandle(String roomHandle){
    this.roomHandle = roomHandle;
}


public void setRoomOther(String roomOther){
    this.roomOther = roomOther;
}


@OneToOne
public Room getRoom(){
    return room;
}


public void setId(Long id){
    this.id = id;
}


public void setOutStorage(OutStorage outStorage){
    this.outStorage = outStorage;
}


@Transient
public Employee getRoomWorker(){
  this.roomWorker = employeerequest.getRoomWorker(this.emp_idU6JN);
return this.roomWorker;
}}



public void setRoomDate(Date roomDate){
    this.roomDate = roomDate;
}


}