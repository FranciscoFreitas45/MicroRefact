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
import com.hmm.Request.DepartmentRequest;
import com.hmm.Request.Impl.DepartmentRequestImpl;
import com.hmm.DTO.Department;
import com.hmm.Request.LeaveRequest;
import com.hmm.Request.Impl.LeaveRequestImpl;
import com.hmm.DTO.Leave;
import com.hmm.Request.WorkRequest;
import com.hmm.Request.Impl.WorkRequestImpl;
import com.hmm.DTO.Work;
import com.hmm.Request.TravelRequest;
import com.hmm.Request.Impl.TravelRequestImpl;
import com.hmm.DTO.Travel;
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

 private Integer dept_idJTC8;

 private Integer dept_idX1TV;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public Set<Bcard> getBcards(){
    return bcards;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public List<SchedulEvent> getSchedulEventlist(){
    return SchedulEventlist;
}


@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEndDate(){
    return endDate;
}


@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
@JoinColumn(name = "dept_id")
public Department getDepartmentes(){
    return departmentes;
}


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


public String getIntroduce(){
    return introduce;
}


public String getEmpName(){
    return empName;
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


public String getEmpNo(){
    return empNo;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public Set<Travel> getTravels(){
    return travels;
}


@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEntryDate(){
    return entryDate;
}


public String getUserName(){
    return userName;
}


public String getAddress(){
    return address;
}


public String getJobtype(){
    return jobtype;
}


public String getEmpImage(){
    return empImage;
}


public String getTel(){
    return tel;
}


public String getPassword(){
    return password;
}


public void setEmpName(String empName){
    this.empName = empName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setEmpName"))

.queryParam("empName",empName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmpSex(String empSex){
    this.empSex = empSex;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setEmpSex"))

.queryParam("empSex",empSex)
;
restTemplate.put(builder.toUriString(),null);
}


public void setJobtype(String jobtype){
    this.jobtype = jobtype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setJobtype"))

.queryParam("jobtype",jobtype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTel(String tel){
    this.tel = tel;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setTel"))

.queryParam("tel",tel)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAddress(String address){
    this.address = address;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setAddress"))

.queryParam("address",address)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIdcard(String idcard){
    this.idcard = null != idcard ? idcard : null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setIdcard"))

.queryParam("idcard",idcard)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEntryDate(Date entryDate){
    this.entryDate = entryDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setEntryDate"))

.queryParam("entryDate",entryDate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIntroduce(String introduce){
    this.introduce = introduce;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setIntroduce"))

.queryParam("introduce",introduce)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGroupRoles(List<GroupRole> groupRoles){
    this.groupRoles = groupRoles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setGroupRoles"))

.queryParam("groupRoles",groupRoles)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDepartmentes(Department departmentes){
this.dept_idX1TV = departmentes.getDepartmentes() ;
departmentrequest.setDepartmentes(departmentes,this.dept_idX1TV);
 this.departmentes = departmentes;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setDepartmentes"))

.queryParam("departmentes",departmentes)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setEmpNo"))

.queryParam("empNo",empNo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserName(String userName){
    this.userName = userName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setUserName"))

.queryParam("userName",userName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmpImage(String empImage){
    this.empImage = empImage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ emp_id).concat("/setEmpImage"))

.queryParam("empImage",empImage)
;
restTemplate.put(builder.toUriString(),null);
}


}