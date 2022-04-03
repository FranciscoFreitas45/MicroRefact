package com.hmm.DTO;
 import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.Work.entity.Bcard;
import com.hmm.Work.entity.Work;
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.department.entity.Department;
import com.hmm.leave.entity.Leave;
import com.hmm.travel.entity.Travel;
import com.hmm.userRole.entity.GroupRole;
import com.hmm.Request.BcardRequest;
import com.hmm.Request.Impl.BcardRequestImpl;
import com.hmm.DTO.Bcard;
import com.hmm.Request.SchedulEventRequest;
import com.hmm.Request.Impl.SchedulEventRequestImpl;
import com.hmm.DTO.SchedulEvent;
import com.hmm.Request.DepartmentRequest;
import com.hmm.Request.Impl.DepartmentRequestImpl;
import com.hmm.DTO.Department;
import com.hmm.Request.BcardRequest;
import com.hmm.Request.Impl.BcardRequestImpl;
import com.hmm.DTO.Bcard;
import com.hmm.Request.SchedulEventRequest;
import com.hmm.Request.Impl.SchedulEventRequestImpl;
import com.hmm.DTO.SchedulEvent;
public class Employee {

 private  Integer emp_id;

 private  String empNo;

 private  String userName;

 private  String empName;

 private  String password;

 private  String empSex;

 private  String idcard;

 private  String tel;

 private  String jobtype;

 private  String address;

 private  String introduce;

 private  Date entryDate;

 private  Date endDate;

 private  String empImage;

 private  List<GroupRole> groupRoles;

 private  Set<Work> works;

 private  Set<Leave> leaves;

 private  Set<Travel> travels;

 private  Department departmentes;

 private  List<SchedulEvent> SchedulEventlist;

 private  Set<Bcard> bcards;

 private BcardRequest bcardrequest = new BcardRequestImpl();;

 private SchedulEventRequest scheduleventrequest = new SchedulEventRequestImpl();;

 private Integer dept_idDMV5;

 private DepartmentRequest departmentrequest = new DepartmentRequestImpl();;

 private BcardRequest bcardrequest = new BcardRequestImpl();;

 private SchedulEventRequest scheduleventrequest = new SchedulEventRequestImpl();;

 private Integer dept_idC3Y6;


public void setPassword(String password){
    this.password = password;
}


public void setDepartmentes(Department departmentes){
this.dept_idDMV5 = departmentes.getDepartmentes() ;
departmentrequest.setDepartmentes(departmentes,this.dept_idDMV5);
 this.departmentes = departmentes;
}



@Transient
public Set<Bcard> getBcards(){
  this.bcards = bcardrequest.getBcards(this.emp_id);
return this.bcards;
}}



public void setIdcard(String idcard){
    this.idcard = null != idcard ? idcard : null;
}


@Transient
public List<SchedulEvent> getSchedulEventlist(){
  this.SchedulEventlist = scheduleventrequest.getSchedulEventlist(this.emp_id);
return this.SchedulEventlist;
}}



public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEndDate(){
    return endDate;
}


public void setLeaves(Set<Leave> leaves){
    this.leaves = leaves;
}


@Transient
public Department getDepartmentes(){
  this.departmentes = departmentrequest.getDepartmentes(this.dept_idDMV5);
return this.departmentes;
}}



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getEmp_id(){
    return emp_id;
}


@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
public List<GroupRole> getGroupRoles(){
    return groupRoles;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public Set<Leave> getLeaves(){
    return leaves;
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


public void setSchedulEventlist(List<SchedulEvent> schedulEventlist){
scheduleventrequest.setSchedulEventlist(schedulEventlist,this.emp_id);
 this.schedulEventlist = schedulEventlist;
}



public String getEmpSex(){
    return empSex;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public Set<Work> getWorks(){
    return works;
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


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public Set<Travel> getTravels(){
    return travels;
}


public void setUserName(String userName){
    this.userName = userName;
}


@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEntryDate(){
    return entryDate;
}


public void setEmpSex(String empSex){
    this.empSex = empSex;
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


public void setGroupRoles(List<GroupRole> groupRoles){
    this.groupRoles = groupRoles;
}


public void setAddress(String address){
    this.address = address;
}


public String getJobtype(){
    return jobtype;
}


public String getEmpImage(){
    return empImage;
}


public void setIntroduce(String introduce){
    this.introduce = introduce;
}


public void setBcards(Set<Bcard> bcards){
bcardrequest.setBcards(bcards,this.emp_id);
 this.bcards = bcards;
}



public void setWorks(Set<Work> works){
    this.works = works;
}


public String getTel(){
    return tel;
}


public void setJobtype(String jobtype){
    this.jobtype = jobtype;
}


public void setEmp_id(Integer emp_id){
    this.emp_id = null != emp_id ? emp_id : null;
}


public void setTravels(Set<Travel> travels){
    this.travels = travels;
}


public void setEmpImage(String empImage){
    this.empImage = empImage;
}


public String getPassword(){
    return password;
}


}