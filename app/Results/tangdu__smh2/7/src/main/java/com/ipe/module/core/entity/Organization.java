package com.ipe.module.core.entity;
 import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import java.util.Set;
@Table(name = "t_cor_organization", schema = "", catalog = "db_work")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Organization extends IDEntity{

 private  String orgCode;

 private  String orgName;

 private  String remark;

 private  Organization parent;

 private  Set<Organization> rows;

 private  Integer sno;


@Column(name = "org_name", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
@Basic
public String getOrgName(){
    return orgName;
}


public void setSno(Integer sno){
    this.sno = sno;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "pid")
public Organization getParent(){
    return parent;
}


public void setRows(Set<Organization> rows){
    this.rows = rows;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setOrgName(String orgName){
    this.orgName = orgName;
}


@Column(name = "sno_", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
@Basic
public Integer getSno(){
    return sno;
}


@Column(name = "remark_", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
@Basic
public String getRemark(){
    return remark;
}


public void setOrgCode(String orgCode){
    this.orgCode = orgCode;
}


public void setParent(Organization parent){
    this.parent = parent;
}


@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent", fetch = FetchType.LAZY)
@OrderBy("sno asc")
public Set<Organization> getRows(){
    return rows;
}


@Column(name = "org_code", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
@Basic
public String getOrgCode(){
    return orgCode;
}


}