package com.qidian.hcm.module.organization.response;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.common.enums.YesNo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@ApiModel
public class PositionResponse {

@ApiModelProperty(value = "主键（岗位ID）", name = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "岗位名称", name = "name", required = true)
 private  String name;

@ApiModelProperty(value = "岗位代码", name = "code", required = true)
 private  String code;

@ApiModelProperty(value = "岗位简称", name = "alias", required = true)
 private  String alias;

@ApiModelProperty(value = "所属部门ID", name = "department_id", required = true)
 private  Long departmentId;

@ApiModelProperty(value = "上级岗位ID", name = "parent_position_id", required = true)
 private  Long parentPositionId;

@ApiModelProperty(value = "职级ID", name = "grade_id", required = true)
 private  Long gradeId;

@ApiModelProperty(value = "所属部门Name", name = "department_name", required = true)
 private  String departmentName;

@ApiModelProperty(value = "上级岗位Name", name = "parent_position_name", required = true)
 private  String parentPositionName;

@ApiModelProperty(value = "职级Name", name = "grade_name", required = true)
 private  String gradeName;

@ApiModelProperty(value = "是否有效", name = "enable", required = true)
 private  YesNo enable;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "生效日期", name = "enable_time", required = true)
 private  Date enableTime;

@ApiModelProperty(value = "自定义字段", name = "custom_field", required = true)
 private  JSONObject customField;

@ApiModelProperty(value = "是否被删除", name = "deleted", required = true)
 private  YesNo deleted;

@ApiModelProperty(value = "部门负责人", name = "master", required = true)
 private  String master;

@ApiModelProperty(value = "是否允许编辑")
 private  boolean enableEdit;


}