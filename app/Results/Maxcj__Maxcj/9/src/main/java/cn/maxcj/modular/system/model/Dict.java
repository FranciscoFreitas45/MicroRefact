package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
@TableName("sys_dict")
public class Dict extends Model<Dict>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  Integer num;

 private  Integer pid;

 private  String name;

 private  String code;

 private  String tips;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setNum(Integer num){
    this.num = num;
}


public void setCode(String code){
    this.code = code;
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
    return "Dict{" + "id=" + id + ", num=" + num + ", pid=" + pid + ", name='" + name + '\'' + ", code='" + code + '\'' + ", tips='" + tips + '\'' + '}';
}


public Integer getPid(){
    return pid;
}


public String getCode(){
    return code;
}


}