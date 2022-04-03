package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
public class Role extends Model<Role>{

 private  long serialVersionUID;

 private  Integer id;

 private  Integer num;

 private  Integer pid;

 private  String name;

 private  Integer deptid;

 private  String tips;

 private  Integer version;


public Integer getVersion(){
    return version;
}


public String getName(){
    return name;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


public String getTips(){
    return tips;
}


public Integer getNum(){
    return num;
}


public Integer getPid(){
    return pid;
}


}