package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
public class Grade extends BaseEntity{

 private  Integer classNo;

 private  Integer gradeNo;

 private  String className;

 private  Major major;

 private  boolean enableFlag;

 private  Date createTime;


public Date getCreateTime(){
    return createTime;
}


public Major getMajor(){
    return major;
}


public String getClassName(){
    return className;
}


public Integer getClassNo(){
    return classNo;
}


public Integer getGradeNo(){
    return gradeNo;
}


}