package org.live.dictionary.entity;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import javax.persistence;
@Entity
@Table(name = "sys_dict")
public class Dictionary extends BaseEntity{

 private  long serialVersionUID;

@Column
 private  String serialNo;

@ManyToOne
@JoinColumn(name = "dict_type_id")
 private  DictType dictType;

@Column(name = "dict_mark")
 private  String dictMark;

@Column(name = "dict_value")
 private  String dictValue;

@Column
 private  String remarks;

@Column(name = "del_flag")
 private  int delFlag;


public void setDictType(DictType dictType){
    this.dictType = dictType;
}


public void setDictValue(String dictValue){
    this.dictValue = dictValue;
}


public String getRemarks(){
    return remarks;
}


public void setDictMark(String dictMark){
    this.dictMark = dictMark;
}


public int getDelFlag(){
    return delFlag;
}


public void setSerialNo(String serialNo){
    this.serialNo = serialNo;
}


public String getDictMark(){
    return dictMark;
}


public String getDictValue(){
    return dictValue;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


public DictType getDictType(){
    return dictType;
}


public String getSerialNo(){
    return serialNo;
}


public void setDelFlag(int delFlag){
    this.delFlag = delFlag;
}


}