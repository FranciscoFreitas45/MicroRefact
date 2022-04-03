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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "公积金方案DTO")
public class HousingFundPlanDTO {

@ApiModelProperty(value = "新增的时候不要添加")
 private  Long id;

@ApiModelProperty(value = "方案名称")
@NotBlank
 private  String name;

@ApiModelProperty(value = "入离职临界点")
@Min(value = 0)
 private  Integer limitPoint;

@ApiModelProperty(value = "生效日期")
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date effectDate;

@Range(min = 0, max = 4)
@ApiModelProperty(value = "小数点保留位数")
 private  Integer pointScale;

@ApiModelProperty(value = "小数点进位规则")
 private  PointRule pointRule;

@ApiModelProperty(value = "社保缴纳上限")
 private  Long limitUp;

@ApiModelProperty(value = "社保缴纳下限")
@Min(value = 0)
 private  Long limitDown;

@ApiModelProperty(value = "公积金（公司）比例")
@Range(min = 0, max = 100)
 private  Double fundEmployerRatio;

@ApiModelProperty(value = "公积金（个人）比例")
@Range(min = 0, max = 100)
 private  Double fundPersonalRatio;

@Range(min = 0, max = 100)
@ApiModelProperty(value = "补充公积金（公司）比例")
 private  Double fundAddingEmployerRatio;

@Range(min = 0, max = 100)
@ApiModelProperty(value = "补充公积金（个人）比例")
 private  Double fundAddingPersonalRatio;


}