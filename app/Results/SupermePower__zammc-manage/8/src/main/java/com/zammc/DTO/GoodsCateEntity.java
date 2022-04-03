package com.zammc.DTO;
 import javax.persistence;
import java.sql.Timestamp;
public class GoodsCateEntity {

 private  long cateId;

 private  String cateIcon;

 private  String cateName;

 private  Integer sort;

 private  Byte status;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  Byte version;

 private  Byte dataStatus;


@Basic
@Column(name = "version")
public Byte getVersion(){
    return version;
}


@Basic
@Column(name = "cate_name")
public String getCateName(){
    return cateName;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


@Basic
@Column(name = "data_status")
public Byte getDataStatus(){
    return dataStatus;
}


public void setCateId(long cateId){
    this.cateId = cateId;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "status")
public Byte getStatus(){
    return status;
}


public void setStatus(Byte status){
    this.status = status;
}


@Basic
@Column(name = "cate_icon")
public String getCateIcon(){
    return cateIcon;
}


@Basic
@Column(name = "sort")
public Integer getSort(){
    return sort;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


@Id
@Column(name = "cate_id")
public long getCateId(){
    return cateId;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


}