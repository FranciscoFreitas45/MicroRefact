package org.jeecgframework.web.system.pojo.base;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_function")
// update-begin--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
@org.hibernate.annotations.Proxy(lazy = false)
public class TSFunction extends IdEntity{

 private  TSFunction TSFunction;

 private  String functionName;

 private  Short functionLevel;

 private  String functionUrl;

 private  Short functionIframe;

 private  String functionOrder;

 private  Short functionType;

 private  TSIcon TSIcon;

 private  String functionIconStyle;

 private  TSIcon TSIconDesk;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;

 private  List<TSFunction> TSFunctions;


public void setTSIconDesk(TSIcon TSIconDesk){
    this.TSIconDesk = TSIconDesk;
}


public void setFunctionUrl(String functionUrl){
    this.functionUrl = functionUrl;
}


@Column(name = "functionorder")
public String getFunctionOrder(){
    return functionOrder;
}


public void setFunctionLevel(Short functionLevel){
    this.functionLevel = functionLevel;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setTSFunctions(List<TSFunction> TSFunctions){
    this.TSFunctions = TSFunctions;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public void setTSIcon(TSIcon tSIcon){
    TSIcon = tSIcon;
}


@Column(name = "functionurl", length = 100)
public String getFunctionUrl(){
    return this.functionUrl;
}


public void setFunctionType(Short functionType){
    this.functionType = functionType;
}


@ManyToOne(fetch = FetchType.EAGER)
// update-end--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
@JoinColumn(name = "iconid")
public TSIcon getTSIcon(){
    return TSIcon;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSFunction")
public List<TSFunction> getTSFunctions(){
    return TSFunctions;
}


public void setTSFunction(TSFunction TSFunction){
    this.TSFunction = TSFunction;
}


@Column(name = "functionname", nullable = false, length = 50)
public String getFunctionName(){
    return this.functionName;
}


public boolean hasSubFunction(List<TSFunction> functions){
    for (TSFunction f : functions) {
        if (f != null && f.getTSFunction() != null) {
            if (f.getTSFunction().getId().equals(this.getId())) {
                return true;
            }
        }
    }
    return false;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "functionlevel")
public Short getFunctionLevel(){
    return this.functionLevel;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setFunctionName(String functionName){
    this.functionName = functionName;
}


@ManyToOne(fetch = FetchType.EAGER)
// update-end--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
@JoinColumn(name = "desk_iconid")
public TSIcon getTSIconDesk(){
    return TSIconDesk;
}


@ManyToOne(fetch = FetchType.EAGER)
// update-end--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
@JoinColumn(name = "parentfunctionid")
public TSFunction getTSFunction(){
    return this.TSFunction;
}


public void setFunctionOrder(String functionOrder){
    this.functionOrder = functionOrder;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setFunctionIconStyle(String functionIconStyle){
    this.functionIconStyle = functionIconStyle;
}


@Column(name = "functiontype")
public Short getFunctionType(){
    return this.functionType;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "functioniframe")
public Short getFunctionIframe(){
    return functionIframe;
}


@Column(name = "function_icon_style")
public String getFunctionIconStyle(){
    return functionIconStyle;
}


public void setFunctionIframe(Short functionIframe){
    this.functionIframe = functionIframe;
}


}