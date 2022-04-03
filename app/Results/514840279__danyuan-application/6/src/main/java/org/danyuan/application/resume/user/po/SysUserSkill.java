package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_user_skill")
@NamedQuery(name = "SysUserSkill.findAll", query = "SELECT s FROM SysUserSkill s")
public class SysUserSkill extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "user_uuid")
 private  String userUuid;

@Column(name = "skill")
 private  String skill;

@Column(name = "month")
 private  Integer month;

@Column(name = "proficiency")
 private  String proficiency;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysUserSkill() {
    super();
}
public void setMonth(Integer month){
    this.month = month;
}


public String getUserUuid(){
    return userUuid;
}


public void setProficiency(String proficiency){
    this.proficiency = proficiency;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public Integer getMonth(){
    return month;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getProficiency(){
    return proficiency;
}


public String getSkill(){
    return skill;
}


}