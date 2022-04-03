package DTO;
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


@Column(name = "functionorder")
public String getFunctionOrder(){
    return functionOrder;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "functionurl", length = 100)
public String getFunctionUrl(){
    return this.functionUrl;
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


@Column(name = "functionname", nullable = false, length = 50)
public String getFunctionName(){
    return this.functionName;
}


@Column(name = "functionlevel")
public Short getFunctionLevel(){
    return this.functionLevel;
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


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "functiontype")
public Short getFunctionType(){
    return this.functionType;
}


@Column(name = "functioniframe")
public Short getFunctionIframe(){
    return functionIframe;
}


@Column(name = "function_icon_style")
public String getFunctionIconStyle(){
    return functionIconStyle;
}


}