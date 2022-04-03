package com.qidian.hcm.module.employee.entity;
 import com.qidian.hcm.module.employee.enums.FormType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "customized_employee_form")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class CustomizedEmployeeForm implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "code")
 private  String code;

@Column(name = "title")
 private  String title;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  FormType type;

@Column(name = "required")
 private  Boolean required;

@Column(name = "multi_record")
 private  Boolean multiRecord;

@Column(name = "on_board")
 private  Boolean onBoard;

@Column(name = "switchable")
 private  Boolean switchable;

@Column(name = "enable")
 private  Boolean enable;

@Column(name = "idx")
 private  Integer idx;

@Column(name = "created_by")
 private  Long createdBy;


}