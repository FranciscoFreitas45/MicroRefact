package com.qidian.hcm.module.salary.entity;
 import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "employee_bank_info")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class EmployeeBankInfo implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "card_no")
 private  String cardNo;

@Column(name = "bank_name")
 private  String bankName;

@Column(name = "bank_address")
 private  String bankAddress;


}