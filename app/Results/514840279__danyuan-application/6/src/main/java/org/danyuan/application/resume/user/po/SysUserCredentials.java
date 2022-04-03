package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_credentials")
@NamedQuery(name = "SysUserCredentials.findAll", query = "SELECT s FROM SysUserCredentials s")
public class SysUserCredentials extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "credentials", columnDefinition = " varchar(20) COMMENT '证书'")
 private  String credentials;

@Column(name = "user_uuid", columnDefinition = " varchar(36) COMMENT '用户id'")
 private  String userUuid;

@Column(name = "gdate", columnDefinition = " varchar(20) COMMENT '获证时间'")
 private  String gdate;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysUserCredentials() {
    super();
}
public String getUserUuid(){
    return userUuid;
}


public void setGdate(String gdate){
    this.gdate = gdate;
}


public String getGdate(){
    return gdate;
}


public String getCredentials(){
    return credentials;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public void setCredentials(String credentials){
    this.credentials = credentials;
}


}