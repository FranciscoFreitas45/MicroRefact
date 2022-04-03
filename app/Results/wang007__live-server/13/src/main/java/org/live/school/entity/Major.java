package org.live.school.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "school_major")
public class Major extends BaseEntity{

@Column
 private  String code;

@Column
 private  String name;

@Column
 private  String description;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@ManyToOne
@JoinColumn(name = "department_id")
 private  Department department;

@Column
 private  boolean enableFlag;


public void setName(String name){
    this.name = name;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public boolean isEnableFlag(){
    return enableFlag;
}


public Department getDepartment(){
    return department;
}


public void setEnableFlag(boolean enableFlag){
    this.enableFlag = enableFlag;
}


public void setCode(String code){
    this.code = code;
}


public void setDepartment(Department department){
    this.department = department;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setDescription(String description){
    this.description = description;
}


public String getCode(){
    return code;
}


public String getDescription(){
    return description;
}


}