package com.qidian.hcm.module.employee.entity;
 import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "employee_work_experience")
@Getter
@Setter
@ApiModel(value = "employee_work_experience")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeWorkExperience implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "start_time")
 private  Date startTime;

@Column(name = "end_time")
 private  Date endTime;

@Column(name = "work_unit")
 private  String workUnit;

@Column(name = "job_position")
 private  String jobPosition;

@Column(name = "leave_reason")
 private  String leaveReason;


}