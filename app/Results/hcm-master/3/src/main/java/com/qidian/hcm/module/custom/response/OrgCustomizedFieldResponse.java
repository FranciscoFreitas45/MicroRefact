package com.qidian.hcm.module.custom.response;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.custom.request.OrgCustomizedFieldRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@ApiModel(value = "组织机构自定义字段列表")
@Getter
@Setter
public class OrgCustomizedFieldResponse extends OrgCustomizedFieldRequest{

@ApiModelProperty(value = "字段类型名称", hidden = true)
 private  String fieldTypeName;

@ApiModelProperty(value = "是否启用")
@Enumerated(EnumType.ORDINAL)
 private  YesNo active;


}