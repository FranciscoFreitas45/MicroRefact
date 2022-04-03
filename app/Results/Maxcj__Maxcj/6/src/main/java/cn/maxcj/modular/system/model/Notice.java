package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
@TableName("sys_notice")
public class Notice extends Model<Notice>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  String title;

 private  Integer type;

 private  String content;

 private  Date createtime;

 private  Integer creater;

 private  Integer isshelian;

 private  Integer deptid;


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public Integer getDeptid(){
    return deptid;
}


public void setTitle(String title){
    this.title = title;
}


public Integer getId(){
    return id;
}


public void setType(Integer type){
    this.type = type;
}


public void setIsshelian(Integer isshelian){
    this.isshelian = isshelian;
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


public void setDeptid(Integer deptid){
    this.deptid = deptid;
}


public Integer getIsshelian(){
    return isshelian;
}


public void setId(Integer id){
    this.id = id;
}


public void setCreater(Integer creater){
    this.creater = creater;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Notice{" + "id=" + id + ", title='" + title + '\'' + ", type=" + type + ", content='" + content + '\'' + ", createtime=" + createtime + ", creater=" + creater + ", isshelian=" + isshelian + ", deptid=" + deptid + '}';
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public Integer getCreater(){
    return creater;
}


}