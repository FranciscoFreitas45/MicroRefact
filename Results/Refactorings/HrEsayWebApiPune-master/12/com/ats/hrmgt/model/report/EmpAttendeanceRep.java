import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmpAttendeanceRep {

@Id
 private  String unid;

 private  String unpaidLeave;

 private  String paidLeave;

 private  String weekOff;

 private  String empPresent;


public void setEmpPresent(String empPresent){
    this.empPresent = empPresent;
}


public String getUnpaidLeave(){
    return unpaidLeave;
}


public void setUnpaidLeave(String unpaidLeave){
    this.unpaidLeave = unpaidLeave;
}


public String getPaidLeave(){
    return paidLeave;
}


public void setUnid(String unid){
    this.unid = unid;
}


public void setWeekOff(String weekOff){
    this.weekOff = weekOff;
}


public String getWeekOff(){
    return weekOff;
}


public String getEmpPresent(){
    return empPresent;
}


@Override
public String toString(){
    return "EmpAttendeanceRep [unid=" + unid + ", unpaidLeave=" + unpaidLeave + ", paidLeave=" + paidLeave + ", weekOff=" + weekOff + ", empPresent=" + empPresent + "]";
}


public String getUnid(){
    return unid;
}


public void setPaidLeave(String paidLeave){
    this.paidLeave = paidLeave;
}


}