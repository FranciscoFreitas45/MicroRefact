package com.qidian.hcm.DTO;
 import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.enums.EmployeeType;
import com.qidian.hcm.module.employee.enums.Gender;
import com.qidian.hcm.module.salary.entity.EmployeeFinancial;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.qidian.hcm.Request.EmployeePositionRequest;
import com.qidian.hcm.Request.Impl.EmployeePositionRequestImpl;
import com.qidian.hcm.DTO.EmployeePosition;
import com.qidian.hcm.Request.EmployeeFinancialRequest;
import com.qidian.hcm.Request.Impl.EmployeeFinancialRequestImpl;
import com.qidian.hcm.DTO.EmployeeFinancial;
public class Employee implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String employeeNo;

 private  String name;

 private  Long avatarFileId;

 private  String mobile;

 private  Gender gender;

 private  Date birthday;

 private  Date hireDate;

 private  Date workDate;

 private  EmployeeType type;

 private  EmployeeStatus status;

 private  Date qualifyDate;

 private  String customizedForms;

 private  String qualifyRemark;

 private  Long qualifyLetterFileId;

 private  String qualifyLetterOriginName;

 private  Date resignationDate;

 private  String resignationReason;

 private  Long resignationLetterFileId;

 private  String resignationLetterOriginName;

 private  Long resignationHandoverManId;

 private  Date createdDate;

 private  Long userId;

 private  boolean superAdmin;

 private  List<EmployeePosition> employeePositions;

 private  EmployeeFinancial employeeFinancial;

 private Long id;


}