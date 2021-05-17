import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
public class EmployeeDTO {

 private  Integer emp_id;

 private  String empNo;

 private  String userName;

 private  String empName;

 private  String password;

 private  String empSex;

 private  String groupName;

 private  String deptName;

 private  String idcard;

 private  String tel;

 private  String jobtype;

 private  String address;

 private  String introduce;

 private  Date entryDate;

 private  Date endDate;

 private  String empImage;


public void setPassword(String password){
    this.password = password;
}


public void setIdcard(String idcard){
    this.idcard = idcard;
}


public void entityToDto(Employee entity,EmployeeDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEndDate(){
    return endDate;
}


public Integer getEmp_id(){
    return emp_id;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public String getIntroduce(){
    return introduce;
}


public String getEmpName(){
    return empName;
}


public String getEmpSex(){
    return empSex;
}


public String getIdcard(){
    return idcard;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setEmpSex(String empSex){
    this.empSex = empSex;
}


@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEntryDate(){
    return entryDate;
}


public void setEntryDate(Date entryDate){
    this.entryDate = entryDate;
}


public String getUserName(){
    return userName;
}


public String getAddress(){
    return address;
}


public void setTel(String tel){
    this.tel = tel;
}


public void setAddress(String address){
    this.address = address;
}


public String getJobtype(){
    return jobtype;
}


public void setIntroduce(String introduce){
    this.introduce = introduce;
}


public String getEmpImage(){
    return empImage;
}


public String getTel(){
    return tel;
}


public void setJobtype(String jobtype){
    this.jobtype = jobtype;
}


public String getDeptName(){
    return deptName;
}


public void setEmp_id(Integer emp_id){
    this.emp_id = emp_id;
}


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEmpImage(String empImage){
    this.empImage = empImage;
}


public String getPassword(){
    return password;
}


public void dtoToEntity(EmployeeDTO dto,Employee entity){
    BeanUtils.copyProperties(dto, entity);
}


}