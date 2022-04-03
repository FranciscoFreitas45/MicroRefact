package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.salary.enums.ThresholdType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "salary_threshold")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SalaryThreshold implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "point")
 private  Integer point;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  ThresholdType type;


}