package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_user_position_rel", schema = "")
@SuppressWarnings("serial")
public class TSUserPositionRelEntity {

 private  java.lang.String id;

@Excel(name = "用户id", width = 15)
 private  java.lang.String userId;

@Excel(name = "职务id", width = 15)
 private  java.lang.String positionId;

@Excel(name = "公司ID", width = 15)
 private  java.lang.String companyId;


public void setPositionId(java.lang.String positionId){
    this.positionId = positionId;
}


@Column(name = "COMPANY_ID", nullable = true, length = 32)
public java.lang.String getCompanyId(){
    return this.companyId;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "POSITION_ID", nullable = true, length = 32)
public java.lang.String getPositionId(){
    return this.positionId;
}


public void setCompanyId(java.lang.String companyId){
    this.companyId = companyId;
}


@Column(name = "USER_ID", nullable = true, length = 32)
public java.lang.String getUserId(){
    return this.userId;
}


public void setUserId(java.lang.String userId){
    this.userId = userId;
}


}