package org.live.school.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "school_department")
public class Department extends BaseEntity{

@Column
 private  String code;

@Column
 private  String name;

@Column
 private  String description;

@Column
 private  boolean enableFlag;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;


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


public void setEnableFlag(boolean enableFlag){
    this.enableFlag = enableFlag;
}


public void setCode(String code){
    this.code = code;
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