package DTO;
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
public class TSInterfaceEntity extends IdEntity{

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String interfaceCode;

 private  java.lang.String interfaceName;

 private  java.lang.String interfaceOrder;

 private  java.lang.String interfaceUrl;

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


@Column(name = "sys_org_code", nullable = false, length = 50)
public String getSysOrgCode(){
    return sysOrgCode;
}


@Column(name = "create_name", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "interface_code", nullable = false)
public java.lang.String getInterfaceCode(){
    return interfaceCode;
}


@Column(name = "create_date", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "update_date", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "update_by", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "interface_order", nullable = true, length = 255)
public java.lang.String getInterfaceOrder(){
    return this.interfaceOrder;
}


@Column(name = "interface_level")
public Short getInterfaceLevel(){
    return this.interfaceLevel;
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


@Column(name = "update_name", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "interface_name", nullable = false, length = 50)
public java.lang.String getInterfaceName(){
    return this.interfaceName;
}


@Column(name = "create_by", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "sys_company_code", nullable = false, length = 50)
public String getSysCompanyCode(){
    return sysCompanyCode;
}


@Column(name = "interface_method", nullable = false)
public java.lang.String getInterfaceMethod(){
    return interfaceMethod;
}


}