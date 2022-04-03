package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
public class Dept extends Model<Dept>{

 private  long serialVersionUID;

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


public Integer getId(){
    return id;
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


public Integer getPid(){
    return pid;
}


public String getPids(){
    return pids;
}


}