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
public class OutStorage {

 private  Long id;

 private  Date outDate;

 private  String reason;

 private  RoomCleanRecord roomCleanRecord;

 private  Employee worker;

 private  List<OutDetailed> outDetailed;

 private  String roomNo;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getReason(){
    return reason;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getOutDate(){
    return outDate;
}


@ManyToOne
public Employee getWorker(){
    return worker;
}


@OneToMany(cascade = CascadeType.ALL, mappedBy = "outStorage", fetch = FetchType.LAZY)
public List<OutDetailed> getOutDetailed(){
    return outDetailed;
}


@OneToOne
public RoomCleanRecord getRoomCleanRecord(){
    return roomCleanRecord;
}


public String getRoomNo(){
    return roomNo;
}


public void setReason(String reason){
    this.reason = reason;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReason"));

.queryParam("reason",reason);
restTemplate.put(builder.toUriString(),null);
}


public void setRoomCleanRecord(RoomCleanRecord roomCleanRecord){
    this.roomCleanRecord = roomCleanRecord;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoomCleanRecord"));

.queryParam("roomCleanRecord",roomCleanRecord);
restTemplate.put(builder.toUriString(),null);
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoomNo"));

.queryParam("roomNo",roomNo);
restTemplate.put(builder.toUriString(),null);
}


}