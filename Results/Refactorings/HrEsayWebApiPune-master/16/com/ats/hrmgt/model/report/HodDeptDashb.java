import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class HodDeptDashb {

@Id
 private  int departId;

 private  String nameSd;

 private  String name;

 private  float reqEmpCount;

 private  float presentDays;

 private  float abDays;

 private  float otLastMonth;

 private  float actualEmpCount;


public void setName(String name){
    this.name = name;
}


public void setPresentDays(float presentDays){
    this.presentDays = presentDays;
}


public void setActualEmpCount(float actualEmpCount){
    this.actualEmpCount = actualEmpCount;
}


public String getName(){
    return name;
}


public String getNameSd(){
    return nameSd;
}


public float getAbDays(){
    return abDays;
}


public void setAbDays(float abDays){
    this.abDays = abDays;
}


public float getPresentDays(){
    return presentDays;
}


public float getActualEmpCount(){
    return actualEmpCount;
}


public int getDepartId(){
    return departId;
}


public void setNameSd(String nameSd){
    this.nameSd = nameSd;
}


public float getOtLastMonth(){
    return otLastMonth;
}


public float getReqEmpCount(){
    return reqEmpCount;
}


public void setDepartId(int departId){
    this.departId = departId;
}


@Override
public String toString(){
    return "HodDeptDashb [departId=" + departId + ", nameSd=" + nameSd + ", name=" + name + ", reqEmpCount=" + reqEmpCount + ", presentDays=" + presentDays + ", abDays=" + abDays + ", otLastMonth=" + otLastMonth + ", actualEmpCount=" + actualEmpCount + "]";
}


public void setOtLastMonth(float otLastMonth){
    this.otLastMonth = otLastMonth;
}


public void setReqEmpCount(float reqEmpCount){
    this.reqEmpCount = reqEmpCount;
}


}