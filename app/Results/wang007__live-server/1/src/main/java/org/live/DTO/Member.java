package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
public class Member extends BaseEntity{

 private  String memberNo;

 private  String realName;

 private  String sex;

 private  Grade grade;

 private  String memberType;

 private  Integer age;

 private  Date birthday;

 private  boolean outDate;

 private  Date registerDate;


public Integer getAge(){
    return age;
}


public Date getBirthday(){
    return birthday;
}


public Date getRegisterDate(){
    return registerDate;
}


public String getRealName(){
    return realName;
}


public String getMemberNo(){
    return memberNo;
}


public String getSex(){
    return sex;
}


public String getMemberType(){
    return memberType;
}


public Grade getGrade(){
    return grade;
}


}