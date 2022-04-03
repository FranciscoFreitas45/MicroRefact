package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
public class Notice extends Model<Notice>{

 private  long serialVersionUID;

 private  Integer id;

 private  String title;

 private  Integer type;

 private  String content;

 private  Date createtime;

 private  Integer creater;

 private  Integer isshelian;

 private  Integer deptid;


public String getContent(){
    return content;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public long getSerialVersionUID(){
    return serialVersionUID;
}


public Integer getType(){
    return type;
}


public Integer getIsshelian(){
    return isshelian;
}


public Integer getCreater(){
    return creater;
}


}