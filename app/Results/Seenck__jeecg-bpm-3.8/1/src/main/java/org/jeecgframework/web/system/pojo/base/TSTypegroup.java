package org.jeecgframework.web.system.pojo.base;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_typegroup")
public class TSTypegroup extends IdEntity{

 private  String typegroupname;

 private  String typegroupcode;

 private  Date createDate;

 private  String createName;

 private  List<TSType> TSTypes;


@Column(name = "typegroupcode", length = 50)
public String getTypegroupcode(){
    return this.typegroupcode;
}


public void setTSTypes(List<TSType> TSTypes){
    this.TSTypes = TSTypes;
}


public void setTypegroupname(String typegroupname){
    this.typegroupname = typegroupname;
}


@Column(name = "create_date")
public Date getCreateDate(){
    return createDate;
}


public void setTypegroupcode(String typegroupcode){
    this.typegroupcode = typegroupcode;
}


public void setCreateDate(Date createDate){
    this.createDate = createDate;
}


@Column(name = "create_name", length = 36)
public String getCreateName(){
    return createName;
}


public void setCreateName(String createName){
    this.createName = createName;
}


@Column(name = "typegroupname", length = 50)
public String getTypegroupname(){
    return this.typegroupname;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSTypegroup")
public List<TSType> getTSTypes(){
    return this.TSTypes;
}


}