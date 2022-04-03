package com.qidian.hcm.module.employee.entity;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.organization.entity.GradeEntity;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
import com.qidian.hcm.Request.OrganizationEntityRequest;
import com.qidian.hcm.Request.Impl.OrganizationEntityRequestImpl;
import com.qidian.hcm.DTO.OrganizationEntity;
import com.qidian.hcm.Request.OrganizationEntityRequest;
import com.qidian.hcm.Request.Impl.OrganizationEntityRequestImpl;
import com.qidian.hcm.DTO.OrganizationEntity;
@Entity
@Table(name = "employee_position")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class EmployeePosition implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "start_date")
 private  Date startDate;

@Column(name = "company_id")
 private  Long companyId;

@Column(name = "department_id")
 private  Long departmentId;

@Column(name = "position_id")
 private  Long positionId;

@Column(name = "grade_id")
 private  Long gradeId;

@Column(name = "leader_id")
 private  Long leaderId;

@Column(name = "customized_field")
 private  String customizedField;

@Transient
 private  JSONObject customizedFieldJson;

@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "position_id", updatable = false, insertable = false)
 private  PositionEntity position;

@Transient
 private  OrganizationEntity department;

@Transient
 private  OrganizationEntity company;

@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "grade_id", updatable = false, insertable = false)
 private  GradeEntity grade;

@Column(name = "id")
 private Long id;

@Transient
 private OrganizationEntityRequest organizationentityrequest = new OrganizationEntityRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private OrganizationEntityRequest organizationentityrequest = new OrganizationEntityRequestImpl();;


}