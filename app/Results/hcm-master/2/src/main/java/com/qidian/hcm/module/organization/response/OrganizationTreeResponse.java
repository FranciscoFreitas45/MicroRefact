package com.qidian.hcm.module.organization.response;
 import com.qidian.hcm.module.organization.enums.OrganizationEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "组织机构Tree对象")
public class OrganizationTreeResponse {

@ApiModelProperty(value = "主键（ID）", name = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "名称", name = "name", required = true)
 private  String name;

@ApiModelProperty(value = "编码", name = "code", required = true)
 private  String code;

@ApiModelProperty(value = "类型", name = "type", required = true)
 private  OrganizationEnums type;

@ApiModelProperty(value = "父公司节点", name = "parent_id", required = true)
 private  Long parentId;

 private  String master;

@ApiModelProperty(value = "子节点", name = "children")
@Transient
 private  List<OrganizationTreeResponse> children;

@ApiModelProperty(value = "是否可点击")
 private  boolean enabled;

@ApiModelProperty(value = "是否可编辑")
 private  boolean enableEdit;

public OrganizationTreeResponse(Long id, String name, String code, OrganizationEnums type, Long parentId, String master) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.type = type;
    this.parentId = parentId;
    this.master = master;
}
public void addChildren(OrganizationTreeResponse organization){
    if (!children.contains(organization)) {
        children.add(organization);
    }
}


}