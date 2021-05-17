import javax.persistence;
@Entity
@Table(name = "tbl_emp_bank_info")
public class TblEmpBankInfo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "bank_info_id")
 private  int bankInfoId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "acc_no")
 private  String accNo;

@Column(name = "bank_id")
 private  int bankId;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public int getBankInfoId(){
    return bankInfoId;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setBanKId(int bankId){
    this.bankId = bankId;
}


public void setBankId(int bankId){
    this.bankId = bankId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getAccNo(){
    return accNo;
}


public void setAccNo(String accNo){
    this.accNo = accNo;
}


public int getBankId(){
    return bankId;
}


public int getDelStatus(){
    return delStatus;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


@Override
public String toString(){
    return "TblEmpBankInfo [bankInfoId=" + bankInfoId + ", empId=" + empId + ", accNo=" + accNo + ", bankId=" + bankId + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setBankInfoId(int bankInfoId){
    this.bankInfoId = bankInfoId;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}