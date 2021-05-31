import javax.persistence;
@Entity
public class DailyDaily {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "ot_hr")
 private  String otHr;

@Column(name = "att_date")
 private  String attDate;

 private  String dayName;


public String getDayName(){
    return dayName;
}


public void setDayName(String dayName){
    this.dayName = dayName;
}


public String getOtHr(){
    return otHr;
}


public String getAttDate(){
    return attDate;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


@Override
public String toString(){
    return "DailyDaily [id=" + id + ", empId=" + empId + ", otHr=" + otHr + ", attDate=" + attDate + ", dayName=" + dayName + "]";
}


public int getEmpId(){
    return empId;
}


public void setAttDate(String attDate){
    this.attDate = attDate;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setOtHr(String otHr){
    this.otHr = otHr;
}


}