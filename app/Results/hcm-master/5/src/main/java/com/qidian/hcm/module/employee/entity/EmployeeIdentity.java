package com.qidian.hcm.module.employee.entity;
 import com.qidian.hcm.module.employee.enums.IdentityType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "employee_identity")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class EmployeeIdentity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  IdentityType type;

@Column(name = "code")
 private  String code;

@Column(name = "customized_field")
 private  String customizedField;


}