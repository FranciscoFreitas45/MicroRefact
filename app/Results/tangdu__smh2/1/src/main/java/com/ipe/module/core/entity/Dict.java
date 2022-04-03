package com.ipe.module.core.entity;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import javax.persistence;
import java.util.Set;
@JsonAutoDetect
@Entity
@Table(name = "t_sys_dict", schema = "", catalog = "db_work")
@JsonIgnoreProperties({ "dictVals" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Dict extends IDEntity{

 private  long serialVersionUID;

 private  String dictName;

 private  String dictCode;

 private  String remark;

 private  Integer sno;

 private  String status;

 private  Set<DictVal> dictVals;


public void setSno(Integer sno){
    this.sno = sno;
}


@Column(name = "dict_name")
public String getDictName(){
    return dictName;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "sno")
public Integer getSno(){
    return sno;
}


@Column(name = "remark_")
public String getRemark(){
    return remark;
}


@OneToMany(cascade = CascadeType.REMOVE)
@JoinColumn(name = "dict_id", referencedColumnName = "id_", updatable = false)
public Set<DictVal> getDictVals(){
    return dictVals;
}


@Column(name = "dict_code")
public String getDictCode(){
    return dictCode;
}


public void setDictVals(Set<DictVal> dictVals){
    this.dictVals = dictVals;
}


public void setDictCode(String dictCode){
    this.dictCode = dictCode;
}


@Column(name = "status")
public String getStatus(){
    return status;
}


public void setDictName(String dictName){
    this.dictName = dictName;
}


public void setStatus(String status){
    this.status = status;
}


}