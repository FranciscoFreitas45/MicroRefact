package com.qidian.hcm.module.organization.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "岗位机构Tree对象")
public class PositionTreeResponse {

@ApiModelProperty(value = "主键（ID）", name = "id", required = true)
 private  Long id;

@Transient
@ApiModelProperty(value = "随机ID", name = "uid", required = true)
 private  String uid;

@ApiModelProperty(value = "名称", name = "name", required = true)
 private  String name;

@ApiModelProperty(value = "编码", name = "code", required = true)
 private  String code;

@ApiModelProperty(value = "父岗位节点", name = "parentPositionId", required = true)
 private  Long parentPositionId;

@ApiModelProperty(value = "子节点", name = "children")
@Transient
 private  List<PositionTreeResponse> children;

public PositionTreeResponse(Long id, String name, String code, Long parentPositionId) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.parentPositionId = parentPositionId;
}
public void addChildren(PositionTreeResponse organization){
    if (!children.contains(organization)) {
        children.add(organization);
    }
}


}