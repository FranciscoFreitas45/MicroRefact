package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_evaluate")
@NamedQuery(name = "SysUserEvaluate.findAll", query = "SELECT s FROM SysUserEvaluate s")
public class SysUserEvaluate extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "type")
 private  String type;

@Column(name = "content")
 private  String content;

@Column(name = "user_uuid")
 private  String userUuid;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysUserEvaluate() {
    super();
}
public void setContent(String content){
    this.content = content;
}


public String getUserUuid(){
    return userUuid;
}


public String getType(){
    return type;
}


public String getContent(){
    return content;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public void setType(String type){
    this.type = type;
}


}