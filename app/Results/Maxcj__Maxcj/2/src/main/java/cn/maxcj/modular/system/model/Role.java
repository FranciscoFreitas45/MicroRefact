package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
@TableName("sys_role")
public class Role extends Model<Role>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  Integer num;

 private  Integer pid;

 private  String name;

 private  Integer deptid;

 private  String tips;

 private  Integer version;


public void setName(String name){
    this.name = name;
}


public Integer getVersion(){
    return version;
}


public String getName(){
    return name;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setNum(Integer num){
    this.num = num;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


public void setTips(String tips){
    this.tips = tips;
}


public void setPid(Integer pid){
    this.pid = pid;
}


public String getTips(){
    return tips;
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
}


public Integer getNum(){
    return num;
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
    return "Role{" + "id=" + id + ", num=" + num + ", pid=" + pid + ", name=" + name + ", deptid=" + deptid + ", tips=" + tips + ", version=" + version + "}";
}


public Integer getPid(){
    return pid;
}


}