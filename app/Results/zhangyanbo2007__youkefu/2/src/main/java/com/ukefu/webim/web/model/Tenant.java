package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_tenant")
@org.hibernate.annotations.Proxy(lazy = false)
public class Tenant {

 private  long serialVersionUID;

 private  String id;

 private  String datasourceid;

 private  String tenantname;

 private  String tenantcode;

 private  boolean systemtenant;

 private  boolean inited;

 private  boolean inites;

 private  boolean initdb;

 private  String adminuser;

 private  String remark;

 private  Date lastmenutime;

 private  Date lastbasetime;

 private  String tenantlogo;

 private  String tenantvalid;

 private  Date createtime;

 private  String password;

 private  String genpasstype;

 private  String sign;

 private  String orgid;


public void setOrgid(String orgid){
    this.orgid = orgid;
}


public void setPassword(String password){
    this.password = password;
}


public boolean isInitdb(){
    return initdb;
}


public void setTenantname(String tenantname){
    this.tenantname = tenantname;
}


public void setAdminuser(String adminuser){
    this.adminuser = adminuser;
}


public String getTenantcode(){
    return tenantcode;
}


public void setInited(boolean inited){
    this.inited = inited;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setTenantvalid(String tenantvalid){
    this.tenantvalid = tenantvalid;
}


public boolean isInites(){
    return inites;
}


public Date getLastbasetime(){
    return lastbasetime;
}


public Date getCreatetime(){
    return createtime;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setId(String id){
    this.id = id;
}


public String getTenantlogo(){
    return tenantlogo;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getDatasourceid(){
    return datasourceid;
}


public boolean isInited(){
    return inited;
}


public String getGenpasstype(){
    return genpasstype;
}


public void setLastmenutime(Date lastmenutime){
    this.lastmenutime = lastmenutime;
}


public void setTenantlogo(String tenantlogo){
    this.tenantlogo = tenantlogo;
}


public Date getLastmenutime(){
    return lastmenutime;
}


public String getOrgid(){
    return orgid;
}


public void setInitdb(boolean initdb){
    this.initdb = initdb;
}


public void setTenantcode(String tenantcode){
    this.tenantcode = tenantcode;
}


public String getTenantvalid(){
    return tenantvalid;
}


public String getAdminuser(){
    return adminuser;
}


public void setSign(String sign){
    this.sign = sign;
}


public void setSystemtenant(boolean systemtenant){
    this.systemtenant = systemtenant;
}


public void setGenpasstype(String genpasstype){
    this.genpasstype = genpasstype;
}


public void setLastbasetime(Date lastbasetime){
    this.lastbasetime = lastbasetime;
}


public String getPassword(){
    return password;
}


public boolean isSystemtenant(){
    return systemtenant;
}


public void setDatasourceid(String datasourceid){
    this.datasourceid = datasourceid;
}


public String getSign(){
    return sign;
}


public void setInites(boolean inites){
    this.inites = inites;
}


public String getTenantname(){
    return tenantname;
}


}