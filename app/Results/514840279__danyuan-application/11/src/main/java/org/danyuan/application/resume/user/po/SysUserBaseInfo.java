package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_base_info")
@NamedQuery(name = "SysUserBaseInfo.findAll", query = "SELECT s FROM SysUserBaseInfo s")
public class SysUserBaseInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "persion_name")
 private  String persionName;

@Column(name = "user_name")
 private  String userName;

@Column(name = "email")
 private  String email;

@Column(name = "education")
 private  String education;

@Column(name = "age", precision = 10)
 private  Integer age;

@Column(name = "statue")
 private  String statue;

@Column(name = "expected_salary")
 private  String expectedSalary;

@Column(name = "addr")
 private  String addr;

@Column(name = "expected_place")
 private  String expectedPlace;

@Column(name = "phone")
 private  String phone;

@Column(name = "university")
 private  String university;

@Column(name = "password")
 private  String password;

@Column(name = "position")
 private  String position;

@Column(name = "work_nature")
 private  String workNature;

@Column(name = "resume_path")
 private  String resumePath;

@Column(name = "major")
 private  String major;

@Column(name = "sex")
 private  String sex;

@Column(name = "ancestral_address")
 private  String ancestralAddress;

@Column(name = "head_pic")
 private  String headPic;

@Column(name = "qq")
 private  String qq;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysUserBaseInfo() {
    super();
}public SysUserBaseInfo(String uuid, String userName, String password) {
    super();
    this.uuid = uuid;
    this.userName = userName;
    this.password = password;
}
public String getPhone(){
    return phone;
}


public Integer getAge(){
    return age;
}


public void setPassword(String password){
    this.password = password;
}


public void setWorkNature(String workNature){
    this.workNature = workNature;
}


public String getAncestralAddress(){
    return ancestralAddress;
}


public String getExpectedSalary(){
    return expectedSalary;
}


public String getExpectedPlace(){
    return expectedPlace;
}


public String getMajor(){
    return major;
}


public void setEducation(String education){
    this.education = education;
}


public String getUniversity(){
    return university;
}


public String getQq(){
    return qq;
}


public String getPersionName(){
    return persionName;
}


public void setPosition(String position){
    this.position = position;
}


public void setPersionName(String persionName){
    this.persionName = persionName;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public String getAddr(){
    return addr;
}


public void setResumePath(String resumePath){
    this.resumePath = resumePath;
}


public String getHeadPic(){
    return headPic;
}


public String getResumePath(){
    return resumePath;
}


public void setExpectedSalary(String expectedSalary){
    this.expectedSalary = expectedSalary;
}


public void setExpectedPlace(String expectedPlace){
    this.expectedPlace = expectedPlace;
}


public void setSex(String sex){
    this.sex = sex;
}


public void setQq(String qq){
    this.qq = qq;
}


public String getEducation(){
    return education;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setHeadPic(String headPic){
    this.headPic = headPic;
}


public void setMajor(String major){
    this.major = major;
}


public void setAncestralAddress(String ancestralAddress){
    this.ancestralAddress = ancestralAddress;
}


public String getPassword(){
    return password;
}


public String getPosition(){
    return position;
}


public void setEmail(String email){
    this.email = email;
}


public void setAddr(String addr){
    this.addr = addr;
}


public String getWorkNature(){
    return workNature;
}


public String getSex(){
    return sex;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "SysUserBaseInfo [uuid=" + uuid + ", userName=" + userName + ", password=" + password + ", persionName=" + persionName + ", sex=" + sex + ", email=" + email + ", phone=" + phone + ", age=" + age + ", headPic=" + headPic + ", major=" + major + ", education=" + education + ", university=" + university + ", qq=" + qq + ", ancestralAddress=" + ancestralAddress + ", addr=" + addr + ", discription=" + discription + ", position=" + position + ", statue=" + statue + ", expectedPlace=" + expectedPlace + ", workNature=" + workNature + ", expectedSalary=" + expectedSalary + ", resumePath=" + resumePath + "]";
}


public void setStatue(String statue){
    this.statue = statue;
}


public void setUniversity(String university){
    this.university = university;
}


public String getStatue(){
    return statue;
}


public void setAge(Integer age){
    this.age = age;
}


}