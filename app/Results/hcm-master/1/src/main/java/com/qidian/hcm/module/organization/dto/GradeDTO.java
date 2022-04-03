package com.qidian.hcm.module.organization.dto;
 import com.qidian.hcm.common.enums.YesNo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
@ApiModel
@Entity
public class GradeDTO {

@Id
@ApiModelProperty(value = "主键（职级代码）", name = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "职级名称", name = "name", required = true)
 private  String name;

@ApiModelProperty(value = "职级简称", name = "alias", required = true)
 private  String alias;

@ApiModelProperty(value = "职级代码", name = "code")
 private  String code;

@ApiModelProperty(value = "职级级别", name = "rank", required = true)
 private  String rank;

@ApiModelProperty(value = "是否有效", name = "enable", required = true)
 private  YesNo enable;

@ApiModelProperty(value = "数据是否被删除", name = "deleted")
@Column(name = "deleted")
 private  YesNo delete;

@ApiModelProperty(value = "", name = "enable_time", required = true)
 private  Date enableTime;

@ApiModelProperty(value = "自定义字段", name = "custom_field")
 private  String customField;


}