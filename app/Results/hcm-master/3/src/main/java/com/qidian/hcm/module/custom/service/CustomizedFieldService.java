package com.qidian.hcm.module.custom.service;
 import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.CommonUtils;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.custom.entity.CustomizedField;
import com.qidian.hcm.module.custom.enums.TargetType;
import com.qidian.hcm.module.custom.repository.CustomizedFieldRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Slf4j
@Service
public class CustomizedFieldService {

@Autowired
 private  CustomizedFieldRespository customizedFieldRespository;


public List<T> findByTargetTypes(List<TargetType> targetTypes,YesNo enable,FieldRowMapper<T> fieldRowMapper){
    List<CustomizedField> customizedFields;
    if (enable == null) {
        customizedFields = customizedFieldRespository.findByTargetTypeInOrderByIdx(targetTypes);
    } else {
        customizedFields = customizedFieldRespository.findByTargetTypeInAndEnableOrderByIdx(targetTypes, enable);
    }
    if (customizedFields.isEmpty()) {
        return Collections.emptyList();
    }
    List<T> dataList = newArrayListWithExpectedSize(customizedFields.size());
    for (CustomizedField customizedField : customizedFields) {
        T t = fieldRowMapper.mapRow(customizedField);
        dataList.add(t);
    }
    return dataList;
}


public boolean checkExists(CustomizedField input){
    CustomizedField temp = customizedFieldRespository.findByTargetTypeAndCode(input.getTargetType(), input.getCode());
    if (input.getId() == null) {
        return temp != null;
    } else {
        return temp != null && !temp.getId().equals(input.getId());
    }
}


public CustomizedField getById(Long id){
    Optional<CustomizedField> optional = customizedFieldRespository.findById(id);
    return optional.orElse(null);
}


public List<T> findByTargetTypeAndTargetIds(TargetType targetType,List<Long> targetIds,FieldRowMapper<T> fieldRowMapper){
    List<CustomizedField> customizedFields = null;
    if (targetIds.size() == 1) {
        customizedFields = customizedFieldRespository.findByTargetTypeAndTargetIdOrderByIdx(targetType, targetIds.get(0));
    } else {
        customizedFields = customizedFieldRespository.findByTargetTypeAndTargetIdInOrderByIdx(targetType, targetIds);
    }
    if (customizedFields.isEmpty()) {
        return Collections.emptyList();
    }
    List<T> dataList = newArrayListWithExpectedSize(customizedFields.size());
    for (CustomizedField customizedField : customizedFields) {
        T t = fieldRowMapper.mapRow(customizedField);
        dataList.add(t);
    }
    return dataList;
}


@Transactional
public void save(T t,CustomizedFieldConverter<T> customizedFieldConverter){
    CustomizedField src = customizedFieldConverter.convert(t);
    boolean exists = this.checkExists(src);
    if (exists) {
        throw new BizException(ResultCode.FIELD_CODE_EXISTS);
    }
    if (src.getId() == null) {
        customizedFieldRespository.save(src);
    } else {
        CustomizedField target = this.getById(src.getId());
        BeanUtils.copyProperties(src, target, CommonUtils.getNullPropertyNames(src));
        customizedFieldRespository.save(target);
    }
}


@Transactional
public void deleteById(Long id){
    boolean exists = customizedFieldRespository.existsById(id);
    if (exists) {
        customizedFieldRespository.deleteById(id);
    }
}


@Transactional
public void toggleActive(Long id){
    CustomizedField customizedField = this.getById(id);
    if (customizedField == null) {
        throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
    }
    YesNo enable = customizedField.getEnable() == YesNo.YES ? YesNo.NO : YesNo.YES;
    customizedField.setEnable(enable);
    customizedFieldRespository.save(customizedField);
}


}