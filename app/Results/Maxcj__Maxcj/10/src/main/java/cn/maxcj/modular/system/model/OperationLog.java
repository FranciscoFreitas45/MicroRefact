package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
@TableName("sys_operation_log")
public class OperationLog extends Model<OperationLog>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  String logtype;

 private  String logname;

 private  Integer userid;

 private  String classname;

 private  String method;

 private  Date createtime;

 private  String succeed;

 private  String message;


public void setLogname(String logname){
    this.logname = logname;
}


public String getClassname(){
    return classname;
}


public String getMessage(){
    return message;
}


public Integer getId(){
    return id;
}


public String getMethod(){
    return method;
}


public void setMessage(String message){
    this.message = message;
}


public String getLogname(){
    return logname;
}


public String getSucceed(){
    return succeed;
}


public void setMethod(String method){
    this.method = method;
}


public String getLogtype(){
    return logtype;
}


public void setSucceed(String succeed){
    this.succeed = succeed;
}


public Date getCreatetime(){
    return createtime;
}


public void setLogtype(String logtype){
    this.logtype = logtype;
}


public void setId(Integer id){
    this.id = id;
}


public void setClassname(String classname){
    this.classname = classname;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "OperationLog{" + "id=" + id + ", logtype=" + logtype + ", logname=" + logname + ", userid=" + userid + ", classname=" + classname + ", method=" + method + ", createtime=" + createtime + ", succeed=" + succeed + ", message=" + message + "}";
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