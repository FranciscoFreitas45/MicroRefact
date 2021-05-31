import javax.persistence;
@Entity
public class TypeWiseRoasterList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "driver_id")
 private  int driverId;

@Column(name = "type_id")
 private  int typeId;

@Column(name = "type_count")
 private  int typeCount;


public int getDriverId(){
    return driverId;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public int getTypeCount(){
    return typeCount;
}


public void setTypeCount(int typeCount){
    this.typeCount = typeCount;
}


@Override
public String toString(){
    return "TypeWiseRoasterList [id=" + id + ", driverId=" + driverId + ", typeId=" + typeId + ", typeCount=" + typeCount + "]";
}


public void setTypeId(int typeId){
    this.typeId = typeId;
}


public void setDriverId(int driverId){
    this.driverId = driverId;
}


public int getTypeId(){
    return typeId;
}


}