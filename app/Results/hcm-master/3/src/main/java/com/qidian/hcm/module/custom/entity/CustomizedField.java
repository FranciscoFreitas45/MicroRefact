package com.qidian.hcm.module.custom.entity;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.custom.enums.FieldType;
import com.qidian.hcm.module.custom.enums.TargetType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "customized_field")
@EntityListeners(AuditingEntityListener.class)
public class CustomizedField implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column
 private  String code;

@Column
@Enumerated(EnumType.STRING)
 private  FieldType fieldType;

@Column
 private  Integer idx;

@Column
@Enumerated(EnumType.STRING)
 private  TargetType targetType;

@Column
 private  Long targetId;

@Column
 private  String attribute;

@Column
@Enumerated(EnumType.ORDINAL)
 private  YesNo enable;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(updatable = false)
 private  Date createdTime;

@Temporal(TemporalType.TIMESTAMP)
@UpdateTimestamp
@Column
 private  Date updateTime;


}