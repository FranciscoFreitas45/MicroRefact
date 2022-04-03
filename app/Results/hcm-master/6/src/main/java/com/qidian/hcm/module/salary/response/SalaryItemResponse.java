package com.qidian.hcm.module.salary.response;
 import com.qidian.hcm.module.salary.enums.PointRule;
import com.qidian.hcm.module.salary.enums.SalaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "薪资项返回Response")
public class SalaryItemResponse {

@ApiModelProperty(value = "主键")
 private  Long id;

@ApiModelProperty(value = "名称", required = true)
@NotBlank
 private  String name;

@ApiModelProperty(value = "税前加项 税前减项  税后加项 税后减项 不直接参与薪资计算项 人力成本项", name = "type", required = true)
@NotNull
 private  SalaryType type;

@ApiModelProperty(value = "小数点保留位数", name = "point_scale", required = true)
@Range(max = 4, min = 0, message = "小数点保留位数只能是0到4位")
 private  Integer pointScale;

@ApiModelProperty(value = "小数点进位规则", name = "point_rule", required = true)
 private  PointRule pointRule;

@ApiModelProperty(value = "公式", name = "formula", required = true)
@NotBlank
 private  String formula;

@ApiModelProperty(value = "是否显示在个人明细", name = "display", required = true)
 private  Boolean display;

@ApiModelProperty(value = "是否可以删除")
 private  Boolean deletable;

@ApiModelProperty(value = "是否可以编辑")
 private  Boolean editable;


@ApiModelProperty(value = "是否内置薪资项")
public Boolean getInternal(){
    return !deletable || !editable;
}


}