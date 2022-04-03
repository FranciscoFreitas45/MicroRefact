package cn.gson.oasys.model.entity.note;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_director")
public class Director {

@Id
@Column(name = "director_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long directorId;

@Column(name = "user_name")
 private  String userName;

 private  String pinyin;

 private  String sex;

@Column(name = "phone_number")
 private  String phoneNumber;

@Column(name = "image_path")
 private  Long attachment;

 private  String remark;

 private  String address;

@Transient
 private  User myuser;

 private  String email;

@Column(name = "company_number")
 private  String companyNumber;

 private  String companyname;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Director(Long directorId, String userName, String pinyin, String sex, String phoneNumber, Long attachment, String remark, String address, User myuser, String email) {
    super();
    this.directorId = directorId;
    this.pinyin = pinyin;
    this.sex = sex;
    this.phoneNumber = phoneNumber;
    this.attachment = attachment;
    this.remark = remark;
    this.address = address;
    this.myuser = myuser;
    this.email = email;
}public Director() {
    super();
// TODO Auto-generated constructor stub
}
public void setDirectorId(Long directorId){
    this.directorId = directorId;
}


public String getCompanyname(){
    return companyname;
}


public void setCompanyNumber(String companyNumber){
    this.companyNumber = companyNumber;
}


public void setPinyin(String pinyin){
    this.pinyin = pinyin;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setMyuser(User myuser){
 userrequest.setMyuser(myuser,this.userId);
}



public String getRemark(){
    return remark;
}


public Long getDirectorId(){
    return directorId;
}


public String getUserName(){
    return userName;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public String getAddress(){
    return address;
}


public User getMyuser(){
  this.myuser = userrequest.getMyuser(this.userId);
return this.myuser;
}


public Long getAttachment(){
    return attachment;
}


public String getCompanyNumber(){
    return companyNumber;
}


public void setAddress(String address){
    this.address = address;
}


public void setSex(String sex){
    this.sex = sex;
}


public String getPinyin(){
    return pinyin;
}


public void setEmail(String email){
    this.email = email;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


public String getSex(){
    return sex;
}


public void setAttachment(Long attachment){
    this.attachment = attachment;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "Director [directorId=" + directorId + ", userName=" + userName + ", pinyin=" + pinyin + ", sex=" + sex + ", phoneNumber=" + phoneNumber + ", attachment=" + attachment + ", remark=" + remark + ", address=" + ", companyNumber=" + companyNumber + ",companyname=" + companyname + "]";
}


public void setCompanyname(String companyname){
    this.companyname = companyname;
}


}