package cn.gson.oasys.model.entity.user;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "aoa_dept")
public class Dept {

@Id
@Column(name = "dept_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long deptId;

@Column(name = "dept_name")
@NotEmpty(message = "部门名称不为空")
 private  String deptName;

@Column(name = "dept_tel")
 private  String deptTel;

@Column(name = "dept_fax")
 private  String deptFax;

 private  String email;

@Column(name = "dept_addr")
 private  String deptAddr;

 private  Long deptmanager;

// @Column(name = "start_time")
// private Date startTime;		//部门上班时间
// @Column(name = "end_time")
// private Date endTime;		//部门下班时间
public Dept() {
}
public void setDeptAddr(String deptAddr){
    this.deptAddr = deptAddr;
}


public Long getDeptId(){
    return deptId;
}


public String getDeptAddr(){
    return deptAddr;
}


public void setDeptmanager(Long deptmanager){
    this.deptmanager = deptmanager;
}


public String getDeptName(){
    return deptName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setDeptFax(String deptFax){
    this.deptFax = deptFax;
}


public void setDeptTel(String deptTel){
    this.deptTel = deptTel;
}


public void setEmail(String email){
    this.email = email;
}


public Long getDeptmanager(){
    return deptmanager;
}


public void setDeptId(Long deptId){
    this.deptId = deptId;
}


public String getDeptTel(){
    return deptTel;
}


public String getDeptFax(){
    return deptFax;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", deptTel=" + deptTel + ", deptFax=" + deptFax + ", email=" + email + ", deptAddr=" + deptAddr + ", deptmanager=" + deptmanager + "]";
}


}