package com.qidian.hcm.module.employee.entity;
 import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "employee_history")
@Getter
@Setter
@ApiModel(value = "employee_history")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class EmployeeHistory implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "content")
 private  String content;

@Column(name = "remark")
 private  String remark;

@Column(name = "happen_date", updatable = false)
 private  Date happenDate;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "created_date", updatable = false)
 private  Date createdDate;

public EmployeeHistory(Long employeeId, String content, Date happenDate, String remark) {
    this.employeeId = employeeId;
    this.content = content;
    this.happenDate = happenDate;
    this.remark = remark;
}
}