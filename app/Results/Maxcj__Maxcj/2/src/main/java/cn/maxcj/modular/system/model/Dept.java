package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
@TableName("sys_dept")
public class Dept extends Model<Dept>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  Integer num;

 private  Integer pid;

 private  String pids;

 private  String simplename;

 private  String fullname;

 private  String tips;

 private  Integer version;


public Integer getVersion(){
    return version;
}


public void setSimplename(String simplename){
    this.simplename = simplename;
}


public void setPids(String pids){
    this.pids = pids;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setNum(Integer num){
    this.num = num;
}


public void setFullname(String fullname){
    this.fullname = fullname;
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


public String getSimplename(){
    return simplename;
}


public String getFullname(){
    return fullname;
}


public String getTips(){
    return tips;
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
    return "Dept{" + "id=" + id + ", num=" + num + ", pid=" + pid + ", pids=" + pids + ", simplename=" + simplename + ", fullname=" + fullname + ", tips=" + tips + ", version=" + version + "}";
}


public Integer getPid(){
    return pid;
}


public String getPids(){
    return pids;
}


}