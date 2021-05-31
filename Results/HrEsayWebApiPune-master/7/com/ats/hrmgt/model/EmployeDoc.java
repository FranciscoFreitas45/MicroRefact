import javax.persistence;
@Entity
@Table(name = "emp_docs")
public class EmployeDoc {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "doc_id")
 private  int docId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "doctype_id")
 private  int doctypeId;

@Column(name = "doc_value")
 private  String docValue;

@Column(name = "doc_image")
 private  String docImage;

@Column(name = "doc_remarks")
 private  String docRemarks;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_user_id ")
 private  int makerUserId;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_int3")
 private  int exInt3;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "ex_var3")
 private  String exVar3;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getExInt1(){
    return exInt1;
}


public void setDocImage(String docImage){
    this.docImage = docImage;
}


public void setDocRemarks(String docRemarks){
    this.docRemarks = docRemarks;
}


public String getExVar1(){
    return exVar1;
}


public void setDocId(int docId){
    this.docId = docId;
}


public int getDoctypeId(){
    return doctypeId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setDoctypeId(int doctypeId){
    this.doctypeId = doctypeId;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getDocRemarks(){
    return docRemarks;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public void setDocValue(String docValue){
    this.docValue = docValue;
}


public int getDocId(){
    return docId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getIsActive(){
    return isActive;
}


public String getDocValue(){
    return docValue;
}


public String getDocImage(){
    return docImage;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "EmployeDoc [docId=" + docId + ", empId=" + empId + ", doctypeId=" + doctypeId + ", docValue=" + docValue + ", docImage=" + docImage + ", docRemarks=" + docRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}