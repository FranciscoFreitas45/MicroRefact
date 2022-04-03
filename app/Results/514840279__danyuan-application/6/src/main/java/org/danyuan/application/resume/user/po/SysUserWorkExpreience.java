package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.danyuan.application.common.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "sys_user_work_expreience")
@NamedQuery(name = "SysUserWorkExpreience.findAll", query = "SELECT s FROM SysUserWorkExpreience s")
public class SysUserWorkExpreience extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "unit")
 private  String unit;

@Column(name = "salary_range")
 private  String salaryRange;

@Column(name = "working_life", precision = 4, scale = 2)
 private  BigDecimal workingLife;

@Column(name = "working_content")
 private  String workingContent;

@Temporal(TemporalType.DATE)
@DateTimeFormat(style = "yyyy-MM-dd")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
@Column(name = "end_date", nullable = false)
 private  Date endDate;

@Column(name = "nature_of_the_firm")
 private  String natureOfTheFirm;

@Temporal(TemporalType.DATE)
@DateTimeFormat(style = "yyyy-MM-dd")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
@Column(name = "start_date", nullable = false)
 private  Date startDate;

@Column(name = "position")
 private  String position;

@Column(name = "working_range")
 private  String workingRange;

@Column(name = "user_uuid")
 private  String userUuid;

@Column(name = "firm_asset_size")
 private  String firmAssetSize;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysUserWorkExpreience() {
    super();
}
public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public String getUserUuid(){
    return userUuid;
}


public void setWorkingLife(BigDecimal workingLife){
    this.workingLife = workingLife;
}


public String getNatureOfTheFirm(){
    return natureOfTheFirm;
}


public Date getEndDate(){
    return endDate;
}


public void setFirmAssetSize(String firmAssetSize){
    this.firmAssetSize = firmAssetSize;
}


public void setWorkingRange(String workingRange){
    this.workingRange = workingRange;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public void setPosition(String position){
    this.position = position;
}


public BigDecimal getWorkingLife(){
    return workingLife;
}


public void setWorkingContent(String workingContent){
    this.workingContent = workingContent;
}


public String getPosition(){
    return position;
}


public String getSalaryRange(){
    return salaryRange;
}


public void setUnit(String unit){
    this.unit = unit;
}


public String getFirmAssetSize(){
    return firmAssetSize;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public void setNatureOfTheFirm(String natureOfTheFirm){
    this.natureOfTheFirm = natureOfTheFirm;
}


public String getUnit(){
    return unit;
}


public String getWorkingContent(){
    return workingContent;
}


public String getWorkingRange(){
    return workingRange;
}


public void setSalaryRange(String salaryRange){
    this.salaryRange = salaryRange;
}


}