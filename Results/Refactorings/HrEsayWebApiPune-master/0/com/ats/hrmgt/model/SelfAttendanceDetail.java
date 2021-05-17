import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class SelfAttendanceDetail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  String id;

 private  String empCode;

 private  String empName;

 private  Date attDate;

 private  String attStatus;

 private  String lvSumupId;


@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAttDate(){
    return attDate;
}


public String getId(){
    return id;
}


public void setAttStatus(String attStatus){
    this.attStatus = attStatus;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getEmpName(){
    return empName;
}


public String getEmpCode(){
    return empCode;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getAttStatus(){
    return attStatus;
}


public void setLvSumupId(String lvSumupId){
    this.lvSumupId = lvSumupId;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "SelfAttendanceDetail [id=" + id + ", empCode=" + empCode + ", empName=" + empName + ", attDate=" + attDate + ", attStatus=" + attStatus + ", lvSumupId=" + lvSumupId + "]";
}


public String getLvSumupId(){
    return lvSumupId;
}


public void setAttDate(Date attDate){
    this.attDate = attDate;
}


}