package cn.gson.oasys.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
public class Dept {

 private  Long deptId;

 private  String deptName;

 private  String deptTel;

 private  String deptFax;

 private  String email;

 private  String deptAddr;

 private  Long deptmanager;

// @Column(name = "start_time")
// private Date startTime;		//部门上班时间
// @Column(name = "end_time")
// private Date endTime;		//部门下班时间
public Dept() {
}
public Long getDeptId(){
    return deptId;
}


public String getDeptAddr(){
    return deptAddr;
}


public String getDeptName(){
    return deptName;
}


public Long getDeptmanager(){
    return deptmanager;
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


}