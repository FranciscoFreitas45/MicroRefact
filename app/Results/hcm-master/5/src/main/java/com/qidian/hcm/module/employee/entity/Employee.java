package com.qidian.hcm.module.employee.entity;
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
@Entity
@Table(name = "employee")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_no")
 private  String employeeNo;

@Column(name = "name")
 private  String name;

@Column(name = "avatar_file_id")
 private  Long avatarFileId;

@Column(name = "mobile")
 private  String mobile;

@Column(name = "gender")
@Enumerated(EnumType.STRING)
 private  Gender gender;

@Column(name = "birthday")
 private  Date birthday;

@Column(name = "hire_date")
 private  Date hireDate;

@Column(name = "work_date")
 private  Date workDate;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  EmployeeType type;

@Column(name = "status")
@Enumerated(EnumType.STRING)
 private  EmployeeStatus status;

@Column(name = "qualify_date")
 private  Date qualifyDate;

@Column(name = "customized_forms")
 private  String customizedForms;

@Column(name = " qualify_remark")
 private  String qualifyRemark;

@Column(name = "qualify_letter_file_id")
 private  Long qualifyLetterFileId;

@Column(name = "qualify_letter_origin_name")
 private  String qualifyLetterOriginName;

@Column(name = "resignation_date")
 private  Date resignationDate;

@Column(name = "resignation_reason")
 private  String resignationReason;

@Column(name = "resignation_letter_file_id")
 private  Long resignationLetterFileId;

@Column(name = "resignation_letter_origin_name")
 private  String resignationLetterOriginName;

@Column(name = "resignation_handover_man_id")
 private  Long resignationHandoverManId;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "created_date", updatable = false)
 private  Date createdDate;

@Column(name = "user_id")
 private  Long userId;

@Column(name = "super_admin")
 private  boolean superAdmin;

@Transient
 private  List<EmployeePosition> employeePositions;

@Transient
 private  EmployeeFinancial employeeFinancial;

@Transient
 private EmployeePositionRequest employeepositionrequest = new EmployeePositionRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private EmployeeFinancialRequest employeefinancialrequest = new EmployeeFinancialRequestImpl();;


}