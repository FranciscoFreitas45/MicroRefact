package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("sys_apply")
public class Apply extends Model<Apply>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  Integer userid;

 private  Integer deptid;

 private  Date applytime;

 private  Integer agree;


public Integer getAgree(){
    return agree;
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
}


public Date getApplytime(){
    return applytime;
}


public void setAgree(Integer agree){
    this.agree = agree;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Apply{" + "id=" + id + ", userid=" + userid + ", deptid=" + deptid + ", applytime=" + applytime + ", agree=" + agree + '}';
}


public Integer getUserid(){
    return userid;
}


public void setApplytime(Date applytime){
    this.applytime = applytime;
}


public void setUserid(Integer userid){
    this.userid = userid;
}


}