package com.qidian.hcm.module.organization.dto;
 import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@Setter
@AllArgsConstructor
@Entity
public class PositionGradeDTO {

@Id
@ApiModelProperty(value = "岗位id", name = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "岗位名称", name = "name", required = true)
 private  String name;

@ApiModelProperty(value = "职级id", name = "gradeId", required = true)
 private  Long gradeId;

@ApiModelProperty(value = "职级名称", name = "gradeName", required = true)
 private  String gradeName;


}