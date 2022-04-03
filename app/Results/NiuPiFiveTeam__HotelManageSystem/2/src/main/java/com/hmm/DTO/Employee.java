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


}