import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
public class GetAdvanceDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "amt")
 private  float amt;

@Column(name = "date")
 private  Date date;

@Column(name = "remark")
 private  String remark;


public void setAmt(float amt){
    this.amt = amt;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setId(String id){
    this.id = id;
}


public void setDate(Date date){
    this.date = date;
}


public String getId(){
    return id;
}


public float getAmt(){
    return amt;
}


@JsonFormat(locale = "ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDate(){
    return date;
}


@Override
public String toString(){
    return "GetAdvanceDetails [id=" + id + ", empId=" + empId + ", amt=" + amt + ", date=" + date + ", remark=" + remark + "]";
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}