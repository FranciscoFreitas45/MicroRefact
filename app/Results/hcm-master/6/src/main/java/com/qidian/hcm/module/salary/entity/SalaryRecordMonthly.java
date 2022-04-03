package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.common.enums.YesNo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "salary_record_monthly")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SalaryRecordMonthly implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "cycle")
 private  String cycle;

@Column(name = "accounted")
@Enumerated(EnumType.ORDINAL)
 private  YesNo accounted;

@Column(name = "recorded")
@Enumerated(EnumType.ORDINAL)
 private  YesNo recorded;

@Column(name = "record_time")
 private  Date recordTime;

@Column(name = "cycle_start_time")
 private  String cycleStartDate;

@Column(name = "cycle_end_time")
 private  String cycleEndDate;


}