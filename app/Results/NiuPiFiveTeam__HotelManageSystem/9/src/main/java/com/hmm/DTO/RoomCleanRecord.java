package com.hmm.DTO;
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
public class RoomCleanRecord {

 private  Long id;

 private  String roomHandle;

 private  String roomOther;

 private  Date roomDate;

 private  Employee roomWorker;

 private  OutStorage outStorage;

 private  Room room;


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


public String getRoomOther(){
    return roomOther;
}


// mappedBy="roomCleanRecord"
@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
public OutStorage getOutStorage(){
    return outStorage;
}


@OneToOne
public Room getRoom(){
    return room;
}


@ManyToOne
public Employee getRoomWorker(){
    return roomWorker;
}


}