package org.danyuan.application.softm.organization.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_department_info")
@NamedQuery(name = "SysDepartmentInfo.findAll", query = "SELECT s FROM SysDepartmentInfo s")
public class SysDepartmentInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "department_name")
 private  String departmentName;

@Column(name = "organization_id")
 private  String organizationId;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDepartmentInfo() {
    super();
}
public String getOrganizationId(){
    return organizationId;
}


public void setDepartmentName(String departmentName){
    this.departmentName = departmentName;
}


public String getDepartmentName(){
    return departmentName;
}


public void setOrganizationId(String organizationId){
    this.organizationId = organizationId;
}


}