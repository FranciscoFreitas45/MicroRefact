package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
@TableName("sys_relation")
public class Relation extends Model<Relation>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  Long menuid;

 private  Integer roleid;


public void setRoleid(Integer roleid){
    this.roleid = roleid;
}


public void setMenuid(Long menuid){
    this.menuid = menuid;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getRoleid(){
    return roleid;
}


public Integer getId(){
    return id;
}


public Long getMenuid(){
    return menuid;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Relation{" + "id=" + id + ", menuid=" + menuid + ", roleid=" + roleid + "}";
}


}