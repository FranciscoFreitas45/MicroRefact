package org.jeecgframework.web.system.pojo.base;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Integer;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_interface", schema = "")
@org.hibernate.annotations.Proxy(lazy = false)
@SuppressWarnings("serial")
public class TSInterfaceEntity extends IdEntity{

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

@Excel(name = "权限编码", width = 15)
 private  java.lang.String interfaceCode;

@Excel(name = "权限名称", width = 15)
 private  java.lang.String interfaceName;

@Excel(name = "排序", width = 15)
 private  java.lang.String interfaceOrder;

@Excel(name = "URL", width = 15)
 private  java.lang.String interfaceUrl;

@Excel(name = "请求方式", width = 15)
 private  java.lang.String interfaceMethod;

 private  Short interfaceLevel;

 private  String sysOrgCode;

 private  String sysCompanyCode;

 private  TSInterfaceEntity tSInterface;

 private  List<TSInterfaceEntity> tSInterfaces;


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "parent_interface_id")
public TSInterfaceEntity gettSInterface(){
    return this.tSInterface;
}


public void setSysCompanyCode(String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


@Column(name = "sys_org_code", nullable = false, length = 50)
public String getSysOrgCode(){
    return sysOrgCode;
}


@Column(name = "create_name", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "interface_code", nullable = false)
public java.lang.String getInterfaceCode(){
    return interfaceCode;
}


public void setInterfaceOrder(java.lang.String interfaceOrder){
    this.interfaceOrder = interfaceOrder;
}


public void settSInterface(TSInterfaceEntity tSInterface){
    this.tSInterface = tSInterface;
}


@Column(name = "create_date", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public void setInterfaceName(java.lang.String interfaceName){
    this.interfaceName = interfaceName;
}


@Column(name = "update_date", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "update_by", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setInterfaceLevel(Short interfaceLevel){
    this.interfaceLevel = interfaceLevel;
}


@Column(name = "interface_order", nullable = true, length = 255)
public java.lang.String getInterfaceOrder(){
    return this.interfaceOrder;
}


@Column(name = "interface_level")
public Short getInterfaceLevel(){
    return this.interfaceLevel;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setSysOrgCode(String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "interface_url", nullable = true, length = 500)
public java.lang.String getInterfaceUrl(){
    if (this.getTSInterfaces() != null && this.getTSInterfaces().size() > 0) {
        return "";
    }
    return this.interfaceUrl;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tSInterface")
public List<TSInterfaceEntity> getTSInterfaces(){
    return tSInterfaces;
}


public void setInterfaceMethod(java.lang.String interfaceMethod){
    this.interfaceMethod = interfaceMethod;
}


@Column(name = "update_name", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "interface_name", nullable = false, length = 50)
public java.lang.String getInterfaceName(){
    return this.interfaceName;
}


public void setTSInterfaces(List<TSInterfaceEntity> tSInterfaces){
    this.tSInterfaces = tSInterfaces;
}


@Column(name = "create_by", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setInterfaceCode(java.lang.String interfaceCode){
    this.interfaceCode = interfaceCode;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setInterfaceUrl(java.lang.String interfaceUrl){
    this.interfaceUrl = interfaceUrl;
}


public boolean hasSubInterface(List<TSInterfaceEntity> tsInterface){
    for (TSInterfaceEntity f : tsInterface) {
        if (f.gettSInterface() != null) {
            if (f.gettSInterface().getId().equals(this.getId())) {
                return true;
            }
        }
    }
    return false;
}


@Column(name = "sys_company_code", nullable = false, length = 50)
public String getSysCompanyCode(){
    return sysCompanyCode;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    /*builder.append("TSInterfaceEntity [createName=");
		builder.append(createName);
		builder.append(", createBy=");
		builder.append(createBy);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateName=");
		builder.append(updateName);
		builder.append(", updateBy=");
		builder.append(updateBy);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", interfaceCode=");
		builder.append(interfaceCode);
		builder.append(", interfaceName=");
		builder.append(interfaceName);
		builder.append(", interfaceOrder=");
		builder.append(interfaceOrder);
		builder.append(", interfaceUrl=");
		builder.append(interfaceUrl);
		builder.append(", interfaceMethod=");
		builder.append(interfaceMethod);
		builder.append(", interfaceLevel=");
		builder.append(interfaceLevel);
		builder.append(", sysOrgCode=");
		builder.append(sysOrgCode);
		builder.append(", sysCompanyCode=");
		builder.append(sysCompanyCode);
		builder.append(", tSInterface=");
		builder.append(tSInterface);
		builder.append(", tSInterfaces=");
		builder.append(tSInterfaces);
		builder.append("]");*/
    return builder.toString();
}


@Column(name = "interface_method", nullable = false)
public java.lang.String getInterfaceMethod(){
    return interfaceMethod;
}


}