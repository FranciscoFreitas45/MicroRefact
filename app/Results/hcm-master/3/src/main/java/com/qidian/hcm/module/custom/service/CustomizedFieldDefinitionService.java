package com.qidian.hcm.module.custom.service;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.module.custom.entity.CustomizedField;
import com.qidian.hcm.module.custom.enums.TargetType;
import com.qidian.hcm.module.custom.request.OrgCustomizedFieldRequest;
import com.qidian.hcm.module.custom.response.OrgCustomizedFieldResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomizedFieldDefinitionService extends CustomizedFieldServiceimplements FieldRowMapper<OrgCustomizedFieldResponse>,CustomizedFieldConverter<OrgCustomizedFieldRequest>{


public List<OrgCustomizedFieldResponse> findByTargetTypes(List<TargetType> targetTypes,YesNo enable){
    return super.findByTargetTypes(targetTypes, enable, this);
}


@Override
public OrgCustomizedFieldResponse mapRow(CustomizedField entity){
    OrgCustomizedFieldResponse response = JSONObject.parseObject(entity.getAttribute(), OrgCustomizedFieldResponse.class);
    BeanUtils.copyProperties(entity, response);
    response.setActive(entity.getEnable());
    response.setFieldTypeName(response.getFieldType().getName());
    return response;
}


public void save(OrgCustomizedFieldRequest request){
    super.save(request, this);
}


@Override
public CustomizedField convert(OrgCustomizedFieldRequest request){
    CustomizedField entity = new CustomizedField();
    BeanUtils.copyProperties(request, entity);
    JSONObject attribute = new JSONObject();
    attribute.put("required", request.isRequired());
    attribute.put("label", request.getLabel());
    attribute.put("length", request.getLength());
    attribute.put("placeholder", request.getPlaceholder());
    attribute.put("options", request.getOptions());
    entity.setAttribute(attribute.toJSONString());
    if (entity.getId() == null) {
        entity.setEnable(YesNo.YES);
    }
    return entity;
}


}