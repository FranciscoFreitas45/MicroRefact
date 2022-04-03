package org.live.school.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.GradeRequest;
import org.live.Request.Impl.GradeRequestImpl;
import org.live.DTO.Grade;
@Entity
@Table(name = "school_member")
public class Member extends BaseEntity{

@Column
 private  String memberNo;

@Column
 private  String realName;

@Column
 private  String sex;

@Transient
 private  Grade grade;

 private  String memberType;

@Column
 private  Integer age;

@Temporal(TemporalType.DATE)
@Column
 private  Date birthday;

@Column
 private  boolean outDate;

@Temporal(TemporalType.TIMESTAMP)
@Column
 private  Date registerDate;

@Column(name = "id2FF1")
 private String id2FF1;

@Transient
 private GradeRequest graderequest = new GradeRequestImpl();;


public void setMemberNo(String memberNo){
    this.memberNo = memberNo;
}


public void setRealName(String realName){
    this.realName = realName;
}


public Integer getAge(){
    return age;
}


public void setMemberType(String memberType){
    this.memberType = memberType;
}


public Date getBirthday(){
    return birthday;
}


public void setSex(String sex){
    this.sex = sex;
}


public void setOutDate(boolean outDate){
    this.outDate = outDate;
}


public Date getRegisterDate(){
    return registerDate;
}


public String getRealName(){
    return realName;
}


public void setBirthday(Date birthday){
    this.birthday = birthday;
}


public String getMemberNo(){
    return memberNo;
}


public String getSex(){
    return sex;
}


public void setGrade(Grade grade){
this.id2FF1 = grade.getGrade() ;
graderequest.setGrade(grade,this.id2FF1);
 this.grade = grade;
}



public void setRegisterDate(Date registerDate){
    this.registerDate = registerDate;
}


public String getMemberType(){
    return memberType;
}


public boolean isOutDate(){
    return outDate;
}


public Grade getGrade(){
  this.grade = graderequest.getGrade(this.id2FF1);
return this.grade;
}}



public void setAge(Integer age){
    this.age = age;
}


}