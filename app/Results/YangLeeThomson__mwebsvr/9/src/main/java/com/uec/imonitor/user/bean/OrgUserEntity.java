package com.uec.imonitor.user.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "auth_org_user")
public class OrgUserEntity {

// 指明这个属性映射为数据库的主键
@Id
// 注解默认使用主键生成方式为自增，hibernate会为我们自动生成一个名为HABERNATE_SEQUENCE的序列
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "user_id")
 private  Integer userId;

@Column(name = "org_id")
 private  Integer orgId;


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public void setOrgId(Integer orgId){
    this.orgId = orgId;
}


public Integer getInnerid(){
    return innerid;
}


public Integer getOrgId(){
    return orgId;
}


public Integer getUserId(){
    return userId;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}