package com.qidian.hcm.module.organization.request;
 import com.qidian.hcm.common.enums.YesNo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "岗位失效的请求")
public class EnablePositionRequest {

@ApiModelProperty(value = "主键（岗位ID）", name = "id", required = true)
 private  List<Long> id;

@ApiModelProperty(value = "disable：1变为失效，0：生效", name = "disable", required = true)
 private  YesNo enable;


}