import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "m_emp_driver")
public class EmpDriver {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int empDriverId;

 private  int designationId;

 private  int empId;

 private  String licenceNo;

 private  Date licenceIssueDate;

 private  Date licenceExpDate;

 private  String vehicleNo;

 private  String vehicleType;

 private  int vehicleTblId;

 private  String exVar1;

 private  String exVar2;

 private  int delStatus;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int makerUserId;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public int getDesignationId(){
    return designationId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLicenceIssueDate(){
    return licenceIssueDate;
}


public void setEmpDriverId(int empDriverId){
    this.empDriverId = empDriverId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setVehicleType(String vehicleType){
    this.vehicleType = vehicleType;
}


public void setLicenceExpDate(Date licenceExpDate){
    this.licenceExpDate = licenceExpDate;
}


public void setLicenceIssueDate(Date licenceIssueDate){
    this.licenceIssueDate = licenceIssueDate;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLicenceExpDate(){
    return licenceExpDate;
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


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setLicenceNo(String licenceNo){
    this.licenceNo = licenceNo;
}


public int getEmpDriverId(){
    return empDriverId;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getVehicleTblId(){
    return vehicleTblId;
}


public void setVehicleTblId(int vehicleTblId){
    this.vehicleTblId = vehicleTblId;
}


public int getDelStatus(){
    return delStatus;
}


public void setVehicleNo(String vehicleNo){
    this.vehicleNo = vehicleNo;
}


public void setDesignationId(int designationId){
    this.designationId = designationId;
}


public String getLicenceNo(){
    return licenceNo;
}


@Override
public String toString(){
    return "EmpDriver [empDriverId=" + empDriverId + ", designationId=" + designationId + ", empId=" + empId + ", licenceNo=" + licenceNo + ", licenceIssueDate=" + licenceIssueDate + ", licenceExpDate=" + licenceExpDate + ", vehicleNo=" + vehicleNo + ", vehicleType=" + vehicleType + ", vehicleTblId=" + vehicleTblId + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", delStatus=" + delStatus + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", makerUserId=" + makerUserId + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public String getVehicleNo(){
    return vehicleNo;
}


public String getVehicleType(){
    return vehicleType;
}


}