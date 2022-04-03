package net.shangtech.weixin.site.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "site_news_type")
public class NewsType extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "type_name")
 private  String name;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "url")
 private  String url;

@Column(name = "memo")
 private  String memo;


public void setName(String name){
    this.name = name;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public String getUrl(){
    return url;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public String getName(){
    return name;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public Integer getSysUserId(){
    return sysUserId;
}


public void setUrl(String url){
    this.url = url;
}


}