package cn.maxcj.modular.system.transfer;
 import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
public class UserDto {

 private  Integer id;

 private  String account;

 private  String password;

 private  String salt;

 private  String name;

@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date birthday;

 private  Integer sex;

 private  String email;

 private  String phone;

 private  String roleid;

 private  Integer deptid;

 private  Integer academy;

 private  Integer status;

 private  Date createtime;

 private  Integer version;

 private  String avatar;


public void setName(String name){
    this.name = name;
}


public String getPhone(){
    return phone;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public String getAvatar(){
    return avatar;
}


public Integer getAcademy(){
    return academy;
}


public String getSalt(){
    return salt;
}


public Integer getId(){
    return id;
}


public Integer getStatus(){
    return status;
}


public Date getCreatetime(){
    return createtime;
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
}


public String getAccount(){
    return account;
}


public void setSalt(String salt){
    this.salt = salt;
}


public void setId(Integer id){
    this.id = id;
}


public void setAcademy(Integer academy){
    this.academy = academy;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public Integer getVersion(){
    return version;
}


public Date getBirthday(){
    return birthday;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setSex(Integer sex){
    this.sex = sex;
}


public String getRoleid(){
    return roleid;
}


public Integer getDeptid(){
    return deptid;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setBirthday(Date birthday){
    this.birthday = birthday;
}


public void setRoleid(String roleid){
    this.roleid = roleid;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public Integer getSex(){
    return sex;
}


public void setAccount(String account){
    this.account = account;
}


public String getEmail(){
    return email;
}


}