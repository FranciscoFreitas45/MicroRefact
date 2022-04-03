package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("sys_message")
public class Message extends Model<Message>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  String title;

 private  String content;

 private  String username;

 private  Date createtime;

@TableField("string_1")
 private  String string1;


public void setContent(String content){
    this.content = content;
}


public void setUsername(String username){
    this.username = username;
}


public String getContent(){
    return content;
}


public void setTitle(String title){
    this.title = title;
}


public String getString1(){
    return string1;
}


public Integer getId(){
    return id;
}


public void setString1(String string1){
    this.string1 = string1;
}


public String getUsername(){
    return username;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
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
    return "Message{" + ", id=" + id + ", title=" + title + ", content=" + content + ", username=" + username + ", createtime=" + createtime + ", string1=" + string1 + "}";
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


}