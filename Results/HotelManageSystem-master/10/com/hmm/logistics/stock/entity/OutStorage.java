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


@ManyToOne
public Employee getWorker(){
    return worker;
}


public void setWorker(Employee worker){
    this.worker = worker;
}


public void setRoomCleanRecord(RoomCleanRecord roomCleanRecord){
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


@OneToOne
public RoomCleanRecord getRoomCleanRecord(){
    return roomCleanRecord;
}


public String getRoomNo(){
    return roomNo;
}


}