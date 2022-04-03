package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_language")
@NamedQuery(name = "SysUserLanguage.findAll", query = "SELECT s FROM SysUserLanguage s")
public class SysUserLanguage extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "user_uuid", columnDefinition = " varchar(36) COMMENT ''")
 private  String userUuid;

@Column(name = "`language`", columnDefinition = " varchar(20) COMMENT '语言'")
 private  String language;

@Column(name = "hearing", columnDefinition = " varchar(20) COMMENT '听力'")
 private  String hearing;

@Column(name = "`read_write`", columnDefinition = " varchar(20) COMMENT '读写'")
 private  String readWrite;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysUserLanguage() {
    super();
}
public String getLanguage(){
    return language;
}


public String getUserUuid(){
    return userUuid;
}


public String getHearing(){
    return hearing;
}


public void setReadWrite(String readWrite){
    this.readWrite = readWrite;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public String getReadWrite(){
    return readWrite;
}


public void setHearing(String hearing){
    this.hearing = hearing;
}


public void setLanguage(String language){
    this.language = language;
}


}