import javax.persistence;
@Entity
@Table(name = "m_bank")
public class Bank {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int bankId;

 private  String name;

 private  String branchName;

 private  String address;

 private  String micrCode;

 private  String ifscCode;

 private  int companyId;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public void setName(String name){
    this.name = name;
}


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public String getName(){
    return name;
}


public int getExInt1(){
    return exInt1;
}


public void setBranchName(String branchName){
    this.branchName = branchName;
}


public void setIfscCode(String ifscCode){
    this.ifscCode = ifscCode;
}


public String getExVar1(){
    return exVar1;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getAddress(){
    return address;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setAddress(String address){
    this.address = address;
}


public String getBranchName(){
    return branchName;
}


public void setBankId(int bankId){
    this.bankId = bankId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setMicrCode(String micrCode){
    this.micrCode = micrCode;
}


public int getBankId(){
    return bankId;
}


public String getMicrCode(){
    return micrCode;
}


public String getIfscCode(){
    return ifscCode;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


@Override
public String toString(){
    return "Bank [bankId=" + bankId + ", name=" + name + ", branchName=" + branchName + ", address=" + address + ", micrCode=" + micrCode + ", ifscCode=" + ifscCode + ", companyId=" + companyId + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}