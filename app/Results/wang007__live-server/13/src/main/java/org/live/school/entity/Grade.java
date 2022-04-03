package org.live.school.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "school_grade")
public class Grade extends BaseEntity{

@Column
 private  Integer classNo;

@Column
 private  Integer gradeNo;

@Column
 private  String className;

@ManyToOne
@JoinColumn(name = "major_id")
 private  Major major;

@Column
 private  boolean enableFlag;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;


public Date getCreateTime(){
    return createTime;
}


public boolean isEnableFlag(){
    return enableFlag;
}


public void setEnableFlag(boolean enableFlag){
    this.enableFlag = enableFlag;
}


public void setClassNo(Integer classNo){
    this.classNo = classNo;
}


public void setGradeNo(Integer gradeNo){
    this.gradeNo = gradeNo;
}


public Major getMajor(){
    return major;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getClassName(){
    return className;
}


public void setClassName(String className){
    this.className = className;
}


public Integer getClassNo(){
    return classNo;
}


public void setMajor(Major major){
    this.major = major;
}


public Integer getGradeNo(){
    return gradeNo;
}


}