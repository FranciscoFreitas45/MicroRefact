package net.shangtech.weixin.site.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "custom_page")
public class CustomPage extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "page_name")
 private  String name;

@Column(name = "temp_id")
 private  Integer temp;

@Column(name = "page_data")
 private  String data;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "sys_user_id")
 private  Integer sysUserId;


public void setName(String name){
    this.name = name;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setData(String data){
    this.data = data;
}


public void setTemp(Integer temp){
    this.temp = temp;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Integer getSysUserId(){
    return sysUserId;
}


public String getData(){
    return data;
}


public Integer getTemp(){
    return temp;
}


}