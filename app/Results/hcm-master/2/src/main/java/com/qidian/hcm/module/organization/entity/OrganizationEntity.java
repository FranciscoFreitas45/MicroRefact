package com.qidian.hcm.module.organization.entity;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.organization.enums.OrganizationEnums;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "organization")
@Getter
@Setter
@ApiModel(value = "organization")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationEntity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "code")
 private  String code;

@Column(name = "name")
@NotNull(message = "部门名称不能为空！")
 private  String name;

@Column(name = "alias")
@NotNull(message = "部门简称不能为空！")
 private  String alias;

@Column(name = "parent_id")
 private  Long parentId;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  OrganizationEnums type;

@Column(name = "enable_time")
 private  Date enableTime;

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
 private  YesNo deleted;

@Column(name = "master")
 private  String master;

@Column(name = "custom_field")
 private  String customField;

@Column(name = "legal_person")
 private  String legalPerson;

@Column(name = "address")
 private  String address;

@Column(name = "register_address")
 private  String registerAddress;

@Column(name = "formation")
 private  Integer formation;

 private  String path;


}