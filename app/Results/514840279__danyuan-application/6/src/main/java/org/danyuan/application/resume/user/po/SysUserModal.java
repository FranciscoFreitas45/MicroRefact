package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_modal")
@NamedQuery(name = "SysUserModal.findAll", query = "SELECT s FROM SysUserModal s")
public class SysUserModal extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "modal_uuid")
 private  String modalUuid;

@Column(name = "user_uuid")
 private  String userUuid;

@Column(name = "use", precision = 10)
 private  Integer use;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysUserModal() {
    super();
}
public String getModalUuid(){
    return modalUuid;
}


public void setModalUuid(String modalUuid){
    this.modalUuid = modalUuid;
}


public String getUserUuid(){
    return userUuid;
}


public void setUse(Integer use){
    this.use = use;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public Integer getUse(){
    return use;
}


}