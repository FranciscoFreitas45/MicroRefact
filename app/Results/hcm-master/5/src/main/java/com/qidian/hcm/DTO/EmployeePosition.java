package com.qidian.hcm.DTO;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.organization.entity.GradeEntity;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
import com.qidian.hcm.Request.OrganizationEntityRequest;
import com.qidian.hcm.Request.Impl.OrganizationEntityRequestImpl;
import com.qidian.hcm.DTO.OrganizationEntity;
import com.qidian.hcm.Request.OrganizationEntityRequest;
import com.qidian.hcm.Request.Impl.OrganizationEntityRequestImpl;
import com.qidian.hcm.DTO.OrganizationEntity;
public class EmployeePosition implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Long employeeId;

 private  Date startDate;

 private  Long companyId;

 private  Long departmentId;

 private  Long positionId;

 private  Long gradeId;

 private  Long leaderId;

 private  String customizedField;

 private  JSONObject customizedFieldJson;

 private  PositionEntity position;

 private  OrganizationEntity department;

 private  OrganizationEntity company;

 private  GradeEntity grade;

 private Long id;

 private Long id;


}