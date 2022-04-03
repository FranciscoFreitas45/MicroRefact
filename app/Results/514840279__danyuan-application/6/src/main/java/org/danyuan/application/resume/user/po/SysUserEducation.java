package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.danyuan.application.common.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "sys_user_education")
@NamedQuery(name = "SysUserEducation.findAll", query = "SELECT s FROM SysUserEducation s")
public class SysUserEducation extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "user_uuid")
 private  String userUuid;

@Temporal(TemporalType.DATE)
@DateTimeFormat(style = "yyyy-MM-dd")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
@Column(name = "graduation_time", nullable = false)
 private  Date graduationTime;

@Column(name = "unified_strategy")
 private  String unifiedStrategy;

@Column(name = "education")
 private  String education;

@Column(name = "length_of_schooling")
 private  String lengthOfSchooling;

@Column(name = "major")
 private  String major;

@Column(name = "graduation_school")
 private  String graduationSchool;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysUserEducation() {
    super();
}
public void setGraduationTime(Date graduationTime){
    this.graduationTime = graduationTime;
}


public String getUserUuid(){
    return userUuid;
}


public void setLengthOfSchooling(String lengthOfSchooling){
    this.lengthOfSchooling = lengthOfSchooling;
}


public String getGraduationSchool(){
    return graduationSchool;
}


public String getUnifiedStrategy(){
    return unifiedStrategy;
}


public String getMajor(){
    return major;
}


public String getEducation(){
    return education;
}


public void setEducation(String education){
    this.education = education;
}


public void setMajor(String major){
    this.major = major;
}


public String getLengthOfSchooling(){
    return lengthOfSchooling;
}


public Date getGraduationTime(){
    return graduationTime;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public void setUnifiedStrategy(String unifiedStrategy){
    this.unifiedStrategy = unifiedStrategy;
}


public void setGraduationSchool(String graduationSchool){
    this.graduationSchool = graduationSchool;
}


}