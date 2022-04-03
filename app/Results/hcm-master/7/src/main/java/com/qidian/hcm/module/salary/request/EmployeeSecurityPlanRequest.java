package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "社保方案Request")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeSecurityPlanRequest {

@ApiModelProperty(value = "方案名称")
@NotNull
@Min(0)
 private  Long socialSecurityPlanId;

@ApiModelProperty(value = "social_security_base")
@NotNull
@Min(0)
 private  Double socialSecurityBase;


}