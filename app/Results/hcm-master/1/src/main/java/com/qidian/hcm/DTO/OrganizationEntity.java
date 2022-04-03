package com.qidian.hcm.DTO;
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
public class OrganizationEntity implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String code;

 private  String name;

 private  String alias;

 private  Long parentId;

 private  OrganizationEnums type;

 private  Date enableTime;

 private  Date createTime;

 private  Date updateTime;

 private  YesNo enable;

 private  YesNo deleted;

 private  String master;

 private  String customField;

 private  String legalPerson;

 private  String address;

 private  String registerAddress;

 private  Integer formation;

 private  String path;


}