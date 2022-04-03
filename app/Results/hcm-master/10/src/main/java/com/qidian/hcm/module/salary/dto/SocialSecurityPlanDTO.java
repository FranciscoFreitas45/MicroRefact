package com.qidian.hcm.module.salary.dto;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.module.salary.enums.PointRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "社保方案实体DTO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SocialSecurityPlanDTO {

@ApiModelProperty(value = "前端不传不要添加", name = "id")
 private  Long id;

@ApiModelProperty(value = "方案名称", name = "name", required = true)
@NotBlank
 private  String name;

@ApiModelProperty(value = "入离职临界点", name = "limit_point", required = true)
 private  Integer limitPoint;

@ApiModelProperty(value = "调整日期", name = "effect_date", required = true)
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date effectDate;

@ApiModelProperty(value = "小数点保留位数", name = "point_scale", required = true)
@Range(min = 0, max = 4)
 private  Integer pointScale;

@ApiModelProperty(value = "小数点进位规则", name = "point_rule", required = true)
 private  PointRule pointRule;

@ApiModelProperty(value = "社保缴纳上限", name = "limit_up", required = true)
@Min(0)
 private  Long limitUp;

@ApiModelProperty(value = "社保缴纳下限", required = true)
@Min(0)
 private  Long limitDown;

@ApiModelProperty(value = "养老保险（个人）比例", required = true)
@Range(min = 0, max = 100)
 private  Double pensionPersonalRatio;

@ApiModelProperty(value = "养老保险（公司）比例", required = true)
@Range(min = 0, max = 100)
 private  Double pensionEmployerRatio;

@ApiModelProperty(value = "医疗保险（个人）比例", required = true)
@Range(min = 0, max = 100)
 private  Double historyPersonalRatio;

@ApiModelProperty(value = "医疗保险（公司）比例", required = true)
@Range(min = 0, max = 100)
 private  Double historyEmployerRatio;

@ApiModelProperty(value = "失业保险（个人）比例", required = true)
@Range(min = 0, max = 100)
 private  Double outworkPersonalRatio;

@ApiModelProperty(value = "失业保险（公司）比例", required = true)
@Range(min = 0, max = 100)
 private  Double outworkEmployerRatio;

@ApiModelProperty(value = "工伤保险（个人）比例", required = true)
@Range(min = 0, max = 100)
 private  Double injuryPersonalRatio;

@ApiModelProperty(value = "工伤保险（公司）比例", required = true)
@Range(min = 0, max = 100)
 private  Double injuryEmployerRatio;

@ApiModelProperty(value = "生育保险（个人）比例", required = true)
@Range(min = 0, max = 100)
 private  Double birthPersonalRatio;

@ApiModelProperty(value = "生育保险（公司）比例", required = true)
@Range(min = 0, max = 100)
 private  Double birthEmployerRatio;


}