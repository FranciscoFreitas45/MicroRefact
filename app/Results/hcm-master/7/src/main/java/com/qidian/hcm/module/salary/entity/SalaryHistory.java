package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.salary.enums.SalaryAjustType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "salary_history")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SalaryHistory implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "before_adjust")
 private  Double beforeAdjust;

@Column(name = "after_adjust")
 private  Double afterAdjust;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "adjust_date", updatable = false)
 private  Date adjustDate;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  SalaryAjustType type;

@Column(name = "remark")
 private  String remark;

@Column(name = "increased")
 private  Double increased;


}