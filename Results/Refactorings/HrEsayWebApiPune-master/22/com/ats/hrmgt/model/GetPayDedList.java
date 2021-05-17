import javax.persistence;
@Entity
public class GetPayDedList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "amt")
 private  float amt;


public void setAmt(float amt){
    this.amt = amt;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public float getAmt(){
    return amt;
}


@Override
public String toString(){
    return "GetPayDedList [id=" + id + ", empId=" + empId + ", amt=" + amt + "]";
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}