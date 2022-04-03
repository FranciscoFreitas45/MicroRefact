package com.qidian.hcm.module.employee.entity;
 import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "employee_emergency_contact")
@Getter
@Setter
@ApiModel(value = "employee_emergency_contact")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeEmergencyContact implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "name")
 private  String name;

@Column(name = "relationship")
 private  String relationship;

@Column(name = "mobile")
 private  String mobile;


}