package org.danyuan.application.resume.user.po;
 import java.io.Serializable;
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
@Table(name = "sys_user_project")
@NamedQuery(name = "SysUserProject.findAll", query = "SELECT s FROM SysUserProject s")
public class SysUserProject extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "hardware_environment")
 private  String hardwareEnvironment;

@Column(name = "user_uuid")
 private  String userUuid;

@Column(name = "project_content")
 private  String projectContent;

@Column(name = "developer_kits")
 private  String developerKits;

@Column(name = "project_name")
 private  String projectName;

@Column(name = "position")
 private  String position;

@Temporal(TemporalType.DATE)
@DateTimeFormat(style = "yyyy-MM-dd")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
@Column(name = "start_date", nullable = false)
 private  Date startDate;

@Column(name = "software_environment")
 private  String softwareEnvironment;

@Column(name = "unit")
 private  String unit;

@Temporal(TemporalType.DATE)
@DateTimeFormat(style = "yyyy-MM-dd")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
@Column(name = "end_date", nullable = false)
 private  Date endDate;

@Column(name = "responsibility_description")
 private  String responsibilityDescription;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysUserProject() {
    super();
}
public String getProjectName(){
    return projectName;
}


public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public void setSoftwareEnvironment(String softwareEnvironment){
    this.softwareEnvironment = softwareEnvironment;
}


public String getUserUuid(){
    return userUuid;
}


public Date getEndDate(){
    return endDate;
}


public String getProjectContent(){
    return projectContent;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public void setPosition(String position){
    this.position = position;
}


public void setProjectName(String projectName){
    this.projectName = projectName;
}


public void setProjectContent(String projectContent){
    this.projectContent = projectContent;
}


public String getPosition(){
    return position;
}


public String getSoftwareEnvironment(){
    return softwareEnvironment;
}


public String getDeveloperKits(){
    return developerKits;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setDeveloperKits(String developerKits){
    this.developerKits = developerKits;
}


public void setUserUuid(String userUuid){
    this.userUuid = userUuid;
}


public String getHardwareEnvironment(){
    return hardwareEnvironment;
}


public String getResponsibilityDescription(){
    return responsibilityDescription;
}


public String getUnit(){
    return unit;
}


public void setResponsibilityDescription(String responsibilityDescription){
    this.responsibilityDescription = responsibilityDescription;
}


public void setHardwareEnvironment(String hardwareEnvironment){
    this.hardwareEnvironment = hardwareEnvironment;
}


}