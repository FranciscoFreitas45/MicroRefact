package com.hmm.employee.entity;
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
@Entity
@Table(name = "t_employee")
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


public void setPassword(String password){
    this.password = password;
}


public void setDepartmentes(Department departmentes){
    this.departmentes = departmentes;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public Set<Bcard> getBcards(){
    return bcards;
}


public void setIdcard(String idcard){
    this.idcard = null != idcard ? idcard : null;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "employ", fetch = FetchType.LAZY)
public List<SchedulEvent> getSchedulEventlist(){
    return SchedulEventlist;
}


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
    SchedulEventlist = schedulEventlist;
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