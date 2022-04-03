package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
public class User extends Model<User>{

 private  long serialVersionUID;

 private  Integer id;

 private  String avatar;

 private  String account;

 private  String password;

 private  String salt;

 private  String name;

 private  Date birthday;

 private  Integer sex;

 private  String email;

 private  String phone;

 private  String roleid;

 private  Integer deptid;

 private  Integer status;

 private  Date createtime;

 private  Integer version;

 private  Integer academy;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getPhone(){
    return phone;
}


public String getName(){
    return name;
}


public String getAvatar(){
    return avatar;
}


public String getSalt(){
    return salt;
}


public Integer getAcademy(){
    return academy;
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


public String getAccount(){
    return account;
}


public Integer getVersion(){
    return version;
}


public Date getBirthday(){
    return birthday;
}


public String getRoleid(){
    return roleid;
}


public Integer getDeptid(){
    return deptid;
}


public String getPassword(){
    return password;
}


public Integer getSex(){
    return sex;
}


public String getEmail(){
    return email;
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDeptid"))

.queryParam("deptid",deptid)
;
restTemplate.put(builder.toUriString(),null);
}


}