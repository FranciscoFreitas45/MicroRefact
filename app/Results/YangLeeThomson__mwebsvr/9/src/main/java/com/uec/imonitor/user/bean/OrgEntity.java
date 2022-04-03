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
@Table(name = "auth_organization")
public class OrgEntity {

// 指明这个属性映射为数据库的主键
@Id
// 注解默认使用主键生成方式为自增，hibernate会为我们自动生成一个名为HABERNATE_SEQUENCE的序列
@GeneratedValue
@Column(name = "org_id", nullable = false)
 private  Integer orgId;

@Column(name = "org_code")
 private  String orgCode;

@Column(name = "org_name")
 private  String orgName;

@Column(name = "parent_org_id")
 private  Integer parentOrgId;

@Column(name = "tenant_id")
 private  Integer tenantId;

@Column(name = "type")
 private  Integer type;


public void setOrgId(Integer orgId){
    this.orgId = orgId;
}


public String getOrgName(){
    return orgName;
}


public Integer getType(){
    return type;
}


public Integer getOrgId(){
    return orgId;
}


public void setOrgName(String orgName){
    this.orgName = orgName;
}


public void setTenantId(Integer tenantId){
    this.tenantId = tenantId;
}


public void setOrgCode(String orgCode){
    this.orgCode = orgCode;
}


public Integer getParentOrgId(){
    return parentOrgId;
}


public Integer getTenantId(){
    return tenantId;
}


public void setType(Integer type){
    this.type = type;
}


public String getOrgCode(){
    return orgCode;
}


public void setParentOrgId(Integer parentOrgId){
    this.parentOrgId = parentOrgId;
}


}