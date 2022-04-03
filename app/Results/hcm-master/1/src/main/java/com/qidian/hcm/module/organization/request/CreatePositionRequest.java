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
@ApiModel(value = "创建岗位的请求类")
@AllArgsConstructor
@NoArgsConstructor
public class CreatePositionRequest {

@NotBlank(message = "岗位名称不能为空")
@Length(min = 1, max = 100)
@ApiModelProperty(value = "岗位名称", required = true)
 private  String name;

@ApiModelProperty(value = "岗位代码", required = true)
@NotBlank(message = "岗位代码不能为空")
@Pattern(regexp = "(^$|[0-9]{8})", message = "岗位代码是8位数字")
 private  String code;

@ApiModelProperty(value = "岗位简称", required = true)
@NotBlank(message = "岗位简称不能为空")
@Length(min = 1, max = 100)
 private  String alias;

@ApiModelProperty(value = "所属部门ID", required = true)
@NotNull(message = "所属部门ID不能为空")
 private  Long departmentId;

@ApiModelProperty(value = "上级岗位ID", required = true)
 private  Long parentPositionId;

@ApiModelProperty(value = "职级ID", required = true)
@NotNull(message = "职级ID不能为空")
 private  Long gradeId;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "生效日期", required = true)
@NotNull(message = "生效日期不能为空")
 private  Date enableTime;

@ApiModelProperty(value = "json格式的自定义字段，{key : 自定义字段的id，value : 自定义字段的值}")
 private  JSONObject customField;


}