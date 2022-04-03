package com.qidian.hcm.DTO;
 import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
public class EmployeeSalaryPageResponse {

 private  Long id;

 private  String name;

 private  String avatar;

 private  Long avatarFileId;

 private  String companyName;

 private  String departmentName;

 private  String employeeNo;

 private  EmployeeStatus status;

 private  Date hireDate;

 private  Date resignationDate;

 private  Double salary;


}