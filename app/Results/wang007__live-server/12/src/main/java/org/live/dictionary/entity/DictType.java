package org.live.dictionary.entity;
 import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import org.live.common.utils.UUIDGenerator;
import javax.persistence;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "sys_dict_type")
public class DictType extends BaseEntity{

 private  long serialVersionUID;

@Column
 private  String serialNo;

@Column(name = "type_name")
 private  String typeName;

@Column(name = "dict_type_mark")
 private  String dictTypeMark;

@Column
 private  String description;

@Column(name = "del_flag")
 private  int delFlag;


public void setDictTypeMark(String dictTypeMark){
    this.dictTypeMark = dictTypeMark;
}


public String getDictTypeMark(){
    return dictTypeMark;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public int getDelFlag(){
    return delFlag;
}


public void setSerialNo(String serialNo){
    this.serialNo = serialNo;
}


public void setDescription(String description){
    this.description = description;
}


public String getTypeName(){
    return typeName;
}


public String getDescription(){
    return description;
}


public String getSerialNo(){
    return serialNo;
}


public void setDelFlag(int delFlag){
    this.delFlag = delFlag;
}


}