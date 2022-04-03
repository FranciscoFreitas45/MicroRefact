package com.qidian.hcm.module.organization.request;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "创建职级的请求类")
@AllArgsConstructor
@NoArgsConstructor
public class CreateGradeRequest {

@ApiModelProperty(value = "职级名称", required = true)
@NotBlank(message = "职级名称不能为空")
@Length(min = 1, max = 100)
 private  String name;

@ApiModelProperty(value = "职级简称", required = true)
@NotBlank(message = "职级简称不能为空")
@Length(min = 1, max = 100)
 private  String alias;

@ApiModelProperty(value = "职级代码", required = true)
@NotBlank(message = "职级代码不能为空")
@Pattern(regexp = "(^$|[0-9]{8})", message = "职级代码是8位数字")
 private  String code;

@ApiModelProperty(value = "职级级别", required = true)
@NotBlank(message = "职级级别不能为空")
@Length(min = 1, max = 100)
 private  String rank;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "生效日期", name = "enable_time", required = true)
@NotNull(message = "生效日期不能为空")
 private  Date enableTime;

@ApiModelProperty(value = "json格式的自定义字段，{key : 自定义字段的id，value : 自定义字段的值}")
 private  JSONObject customField;


}