import javax.persistence;
@Entity
public class EmployeeListForInActive {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "notice_pay_amount")
 private  int noticePayAmount;


public void setNoticePayAmount(int noticePayAmount){
    this.noticePayAmount = noticePayAmount;
}


public String getEmpCode(){
    return empCode;
}


public int getNoticePayAmount(){
    return noticePayAmount;
}


@Override
public String toString(){
    return "EmployeeListForInActive [empId=" + empId + ", empCode=" + empCode + ", noticePayAmount=" + noticePayAmount + "]";
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}