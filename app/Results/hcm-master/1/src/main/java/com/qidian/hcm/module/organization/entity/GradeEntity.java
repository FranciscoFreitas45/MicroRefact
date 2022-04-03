package com.qidian.hcm.module.organization.entity;
 import com.qidian.hcm.common.enums.YesNo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "grade")
@Getter
@Setter
@ApiModel(value = "grade")
@EntityListeners(AuditingEntityListener.class)
public class GradeEntity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "alias")
 private  String alias;

@Column(name = "code")
 private  String code;

@Column(name = "rank")
 private  String rank;

@Column(name = "custom_field")
 private  String customField;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "create_time", updatable = false)
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@UpdateTimestamp
@Column(name = "update_time", insertable = false)
 private  Date updateTime;

@Column(name = "enable")
@Enumerated(EnumType.ORDINAL)
 private  YesNo enable;

@Column(name = "deleted")
@Enumerated(EnumType.ORDINAL)
 private  YesNo delete;

@Column(name = "enable_time")
 private  Date enableTime;


}