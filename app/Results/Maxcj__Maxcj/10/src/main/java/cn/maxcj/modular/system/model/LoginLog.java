package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
@TableName("sys_login_log")
public class LoginLog extends Model<LoginLog>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  String logname;

 private  Integer userid;

 private  Date createtime;

 private  String succeed;

 private  String message;

 private  String ip;


public void setLogname(String logname){
    this.logname = logname;
}


public String getIp(){
    return ip;
}


public String getMessage(){
    return message;
}


public Integer getId(){
    return id;
}


public void setMessage(String message){
    this.message = message;
}


public void setIp(String ip){
    this.ip = ip;
}


public String getLogname(){
    return logname;
}


public String getSucceed(){
    return succeed;
}


public void setSucceed(String succeed){
    this.succeed = succeed;
}


public Date getCreatetime(){
    return createtime;
}


public void setId(Integer id){
    this.id = id;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "LoginLog{" + "id=" + id + ", logname=" + logname + ", userid=" + userid + ", createtime=" + createtime + ", succeed=" + succeed + ", message=" + message + ", ip=" + ip + "}";
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public Integer getUserid(){
    return userid;
}


public void setUserid(Integer userid){
    this.userid = userid;
}


}