package org.jeecgframework.web.cgreport.page.core;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigParamEntity;
@Entity
@Table(name = "jform_cgreport_head", schema = "")
@SuppressWarnings("serial")
public class CgreportConfigHeadPage {

 private  List<CgreportConfigItemEntity> cgreportConfigItemList;

 private  List<CgreportConfigParamEntity> cgreportConfigParamList;

 private  java.lang.String id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.String cgrSql;

 private  java.lang.String content;


public void setName(java.lang.String name){
    this.name = name;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "NAME", nullable = false, length = 100)
public java.lang.String getName(){
    return this.name;
}


public void setCode(java.lang.String code){
    this.code = code;
}


@Column(name = "CONTENT", nullable = false, length = 1000)
public java.lang.String getContent(){
    return this.content;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "CGR_SQL", nullable = false, length = 2000)
public java.lang.String getCgrSql(){
    return this.cgrSql;
}


public List<CgreportConfigItemEntity> getCgreportConfigItemList(){
    return cgreportConfigItemList;
}


public void setCgreportConfigParamList(List<CgreportConfigParamEntity> cgreportConfigParamList){
    this.cgreportConfigParamList = cgreportConfigParamList;
}


public void setId(java.lang.String id){
    this.id = id;
}


public void setCgreportConfigItemList(List<CgreportConfigItemEntity> cgreportConfigItemList){
    this.cgreportConfigItemList = cgreportConfigItemList;
}


public List<CgreportConfigParamEntity> getCgreportConfigParamList(){
    return cgreportConfigParamList;
}


@Column(name = "CODE", nullable = false, length = 36)
public java.lang.String getCode(){
    return this.code;
}


public void setCgrSql(java.lang.String cgrSql){
    this.cgrSql = cgrSql;
}


}