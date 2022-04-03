package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysUserBaseInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  String persionName;

 private  String userName;

 private  String email;

 private  String education;

 private  Integer age;

 private  String statue;

 private  String expectedSalary;

 private  String addr;

 private  String expectedPlace;

 private  String phone;

 private  String university;

 private  String password;

 private  String position;

 private  String workNature;

 private  String resumePath;

 private  String major;

 private  String sex;

 private  String ancestralAddress;

 private  String headPic;

 private  String qq;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";

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


public String getUniversity(){
    return university;
}


public String getQq(){
    return qq;
}


public String getPersionName(){
    return persionName;
}


public String getUserName(){
    return userName;
}


public String getAddr(){
    return addr;
}


public String getHeadPic(){
    return headPic;
}


public String getResumePath(){
    return resumePath;
}


public String getEducation(){
    return education;
}


public String getPassword(){
    return password;
}


public String getPosition(){
    return position;
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


public String getStatue(){
    return statue;
}


public void setResumePath(String resumePath){
    this.resumePath = resumePath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setResumePath"))

.queryParam("resumePath",resumePath)
;
restTemplate.put(builder.toUriString(),null);
}


}