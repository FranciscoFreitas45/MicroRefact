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
@Table(name = "position")
@Getter
@Setter
@ApiModel(value = "position")
@EntityListeners(AuditingEntityListener.class)
public class PositionEntity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "code")
 private  String code;

@Column(name = "alias")
 private  String alias;

@Column(name = "department_id")
 private  Long departmentId;

@Column(name = "parent_position_id")
 private  Long parentPositionId;

@Column(name = "grade_id")
 private  Long gradeId;

@Column(name = "enable")
@Enumerated(EnumType.ORDINAL)
 private  YesNo enable;

@CreationTimestamp
@Column(name = "create_time", updatable = false)
 private  Date createTime;

@UpdateTimestamp
@Column(name = "update_time", insertable = false)
 private  Date updateTime;

@Column(name = "enable_time")
 private  Date enableTime;

@Column(name = "custom_field")
 private  String customField;

@Column(name = "deleted")
@Enumerated(EnumType.ORDINAL)
 private  YesNo deleted;


}