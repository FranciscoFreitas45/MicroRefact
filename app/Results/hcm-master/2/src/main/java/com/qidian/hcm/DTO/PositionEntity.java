package com.qidian.hcm.DTO;
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
public class PositionEntity implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String name;

 private  String code;

 private  String alias;

 private  Long departmentId;

 private  Long parentPositionId;

 private  Long gradeId;

 private  YesNo enable;

 private  Date createTime;

 private  Date updateTime;

 private  Date enableTime;

 private  String customField;

 private  YesNo deleted;


}