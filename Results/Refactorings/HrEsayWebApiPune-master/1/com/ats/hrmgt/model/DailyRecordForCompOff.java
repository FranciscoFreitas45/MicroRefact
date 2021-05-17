import javax.persistence;
@Entity
public class DailyRecordForCompOff {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "att_date")
 private  String attDate;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "att_status")
 private  String attStatus;


public String getAttStatus(){
    return attStatus;
}


public String getAttDate(){
    return attDate;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


@Override
public String toString(){
    return "DailyRecordForCompOff [id=" + id + ", attDate=" + attDate + ", empId=" + empId + ", lvSumupId=" + lvSumupId + ", attStatus=" + attStatus + "]";
}


public int getLvSumupId(){
    return lvSumupId;
}


public void setAttStatus(String attStatus){
    this.attStatus = attStatus;
}


public void setAttDate(String attDate){
    this.attDate = attDate;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}