package org.live.school.vo;
 import java.util.Date;
public class GradeVo {

 private  String id;

 private  Integer classNo;

 private  Integer gradeNo;

 private  String className;

 private  String majorName;

 private  Date createTime;

 private  boolean enableFlag;

public GradeVo(String id, Integer classNo, Integer gradeNo, String className, String majorName, Date createTime, boolean enableFlag) {
    this.id = id;
    /**
     * 班级号
     */
    this.classNo = classNo;
    /**
     * 年级号
     */
    this.gradeNo = gradeNo;
    /**
     * 班级名称
     */
    this.className = className;
    /**
     * 专业名称
     */
    this.majorName = majorName;
    /**
     * 创建时间
     */
    this.createTime = createTime;
    /**
     * 启用标记
     */
    this.enableFlag = enableFlag;
}
public String getMajorName(){
    return majorName;
}


public void setMajorName(String majorName){
    this.majorName = majorName;
}


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


public String getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setClassName(String className){
    this.className = className;
}


public Integer getClassNo(){
    return classNo;
}


public void setGradeNo(Integer gradeNo){
    this.gradeNo = gradeNo;
}


public void setId(String id){
    this.id = id;
}


public String getClassName(){
    return className;
}


public Integer getGradeNo(){
    return gradeNo;
}


}