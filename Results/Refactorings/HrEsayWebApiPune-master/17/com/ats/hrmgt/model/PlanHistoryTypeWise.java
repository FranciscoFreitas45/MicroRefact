import javax.persistence;
@Entity
public class PlanHistoryTypeWise {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "type_id")
 private  int typeId;

@Column(name = "type_name")
 private  String typeName;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "count")
 private  int count;

@Column(name = "incentive")
 private  float incentive;

@Column(name = "km")
 private  int km;


public void setKm(int km){
    this.km = km;
}


public int getKm(){
    return km;
}


public void setTypeId(int typeId){
    this.typeId = typeId;
}


public int getTypeId(){
    return typeId;
}


public float getIncentive(){
    return incentive;
}


public void setIncentive(float incentive){
    this.incentive = incentive;
}


public int getDelStatus(){
    return delStatus;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


@Override
public String toString(){
    return "PlanHistoryTypeWise [typeId=" + typeId + ", typeName=" + typeName + ", delStatus=" + delStatus + ", count=" + count + ", incentive=" + incentive + ", km=" + km + "]";
}


public String getTypeName(){
    return typeName;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


}