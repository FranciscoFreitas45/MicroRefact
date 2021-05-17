import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_FloorVoRoomVoRoomClean")
public class FloorVoRoomVoRoomClean {

 public  Long id;

 private  String floorName;

 private  String roomNo;

 private  String type;

 private  String roomCleanState;

 private  String roomOther;


public String getRoomCleanState(){
    return roomCleanState;
}


public String getRoomOther(){
    return roomOther;
}


public void setRoomOther(String roomOther){
    this.roomOther = roomOther;
}


public String getType(){
    return type;
}


public void setRoomCleanState(String roomCleanState){
    this.roomCleanState = roomCleanState;
}


public String getFloorName(){
    return floorName;
}


public void setFloorName(String floorName){
    this.floorName = floorName;
}


public void setId(Long id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
}


public void setType(String type){
    this.type = type;
}


public String getRoomNo(){
    return roomNo;
}


}