package org.danyuan.application.softm.organization.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_organization_info")
@NamedQuery(name = "SysOrganizationInfo.findAll", query = "SELECT s FROM SysOrganizationInfo s")
public class SysOrganizationInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "organization_code")
 private  String organizationCode;

@Column(name = "organization_name")
 private  String organizationName;

@Column(name = "organization_address")
 private  String organizationAddress;

@Column(name = "organization_email")
 private  String organizationEmail;

@Column(name = "organization_phone")
 private  String organizationPhone;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysOrganizationInfo() {
    super();
}
public String getOrganizationEmail(){
    return organizationEmail;
}


public String getOrganizationName(){
    return organizationName;
}


public void setOrganizationCode(String organizationCode){
    this.organizationCode = organizationCode;
}


public void setOrganizationAddress(String organizationAddress){
    this.organizationAddress = organizationAddress;
}


public void setOrganizationPhone(String organizationPhone){
    this.organizationPhone = organizationPhone;
}


public String getOrganizationAddress(){
    return organizationAddress;
}


public String getOrganizationCode(){
    return organizationCode;
}


public String getOrganizationPhone(){
    return organizationPhone;
}


public void setOrganizationName(String organizationName){
    this.organizationName = organizationName;
}


public void setOrganizationEmail(String organizationEmail){
    this.organizationEmail = organizationEmail;
}


}