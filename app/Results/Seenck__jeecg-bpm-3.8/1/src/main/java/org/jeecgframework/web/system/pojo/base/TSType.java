package org.jeecgframework.web.system.pojo.base;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Table(name = "t_s_type")
public class TSType extends IdEntity{

 private  TSTypegroup TSTypegroup;

 private  TSType TSType;

 private  String typename;

 private  String typecode;

 private  Date createDate;

 private  String createName;

 private  List<TSType> TSTypes;

 private  Integer orderNum;


public void setTypecode(String typecode){
    this.typecode = typecode;
}


public void setTSType(TSType TSType){
    this.TSType = TSType;
}


@Column(name = "order_num", length = 3)
public Integer getOrderNum(){
    return orderNum;
}


public void setTSTypegroup(TSTypegroup TSTypegroup){
    this.TSTypegroup = TSTypegroup;
}


@Column(name = "create_name", length = 36)
public String getCreateName(){
    return createName;
}


public void setTypename(String typename){
    this.typename = typename;
}


@Column(name = "typecode", length = 50)
public String getTypecode(){
    return this.typecode;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "typegroupid")
public TSTypegroup getTSTypegroup(){
    return this.TSTypegroup;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "typepid")
public TSType getTSType(){
    return this.TSType;
}


@Column(name = "typename", length = 50)
public String getTypename(){
    return this.typename;
}


public void setTSTypes(List<TSType> TSTypes){
    this.TSTypes = TSTypes;
}


@Column(name = "create_date")
public Date getCreateDate(){
    return createDate;
}


public void setCreateDate(Date createDate){
    this.createDate = createDate;
}


public void setCreateName(String createName){
    this.createName = createName;
}


public void setOrderNum(Integer orderNum){
    this.orderNum = orderNum;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSType")
public List<TSType> getTSTypes(){
    return this.TSTypes;
}


}