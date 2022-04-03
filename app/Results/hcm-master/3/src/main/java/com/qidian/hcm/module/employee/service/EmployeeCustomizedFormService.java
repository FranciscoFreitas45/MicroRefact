package com.qidian.hcm.module.employee.service;
 import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.custom.entity.CustomizedField;
import com.qidian.hcm.module.custom.enums.TargetType;
import com.qidian.hcm.module.custom.repository.CustomizedFieldRespository;
import com.qidian.hcm.module.custom.service.CustomizedFieldService;
import com.qidian.hcm.module.employee.dto.CustomizedEmployeeFormDTO;
import com.qidian.hcm.module.employee.dto.FormFieldDTO;
import com.qidian.hcm.module.employee.entity.CustomizedEmployeeForm;
import com.qidian.hcm.module.employee.enums.FormType;
import com.qidian.hcm.module.employee.repository.CustomizedEmployeeFormRepository;
import com.qidian.hcm.module.employee.request.CustomizedFormsFieldsRequest;
import com.qidian.hcm.module.employee.request.CustomizedFormsRequest;
import com.qidian.hcm.module.employee.response.CustomizedFormLayoutResponse;
import com.qidian.hcm.module.employee.response.CustomizedFormResponse;
import com.qidian.hcm.module.employee.response.CustomizedFormsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.lang.Boolean;
import java.util.stream.Collectors.groupingBy;
import java.util.stream.Collectors.toList;
import org.springframework.beans.BeanUtils.copyProperties;
@Service
public class EmployeeCustomizedFormService {

@Autowired
 private  CustomizedEmployeeFormRepository customizedEmployeeFormRepository;

@Autowired
 private  CustomizedFieldService customizedFieldService;

@Autowired
 private  CustomizedFieldRespository customizedFieldRespository;


public CustomizedFormsResponse getCustomizedForms(){
    List<CustomizedEmployeeForm> forms = customizedEmployeeFormRepository.findAll();
    Map<FormType, List<CustomizedEmployeeForm>> groupedForms = forms.stream().sorted(Comparator.comparingInt(CustomizedEmployeeForm::getIdx)).collect(groupingBy(CustomizedEmployeeForm::getType));
    return new CustomizedFormsResponse(convertFormListToDTOList(groupedForms.get(FormType.position)), convertFormListToDTOList(groupedForms.get(FormType.basic)), convertFormListToDTOList(groupedForms.get(FormType.other)));
}


public CustomizedFormResponse getCustomizedFormsDetail(Long id){
    CustomizedEmployeeForm customizedEmployeeForm = customizedEmployeeFormRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.CUSTOMIZED_FORM_IS_NULL));
    CustomizedFormResponse customizedFormResponse = new CustomizedFormResponse();
    copyProperties(customizedEmployeeForm, customizedFormResponse);
    List<CustomizedField> list = customizedFieldRespository.findByTargetTypeAndTargetIdOrderByIdx(TargetType.employee_form, id);
    List<FormFieldDTO> formFieldDTOList = new ArrayList<>();
    list.forEach(customizedField -> {
        FormFieldDTO formFieldDTO = new FormFieldDTO();
        copyProperties(customizedField, formFieldDTO);
        if (!StringUtils.isEmpty(customizedField.getAttribute())) {
            formFieldDTO.setAttribute(JSONObject.parseObject(customizedField.getAttribute()));
        }
        formFieldDTOList.add(formFieldDTO);
    });
    customizedFormResponse.setFields(formFieldDTOList);
    return customizedFormResponse;
}


@Transactional
public void toggleActiveCustomizedForms(Long id,Boolean enable){
    customizedEmployeeFormRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.CUSTOMIZED_FORM_IS_NULL));
    customizedEmployeeFormRepository.enabledCustomizedEmployeeForm(id, enable);
}


@Transactional
public void sortForms(List<Long> ids){
    List<CustomizedEmployeeForm> list = customizedEmployeeFormRepository.findAllById(ids);
    if (list.size() != ids.size()) {
        throw new BizException(ResultCode.PARAM_INVALID);
    }
    list = list.stream().peek(form -> form.setIdx(ids.indexOf(form.getId()) + 1)).collect(toList());
    customizedEmployeeFormRepository.saveAll(list);
}


@Transactional
public void deleteCustomizedForms(Long id){
    CustomizedEmployeeForm customizedEmployeeForm = customizedEmployeeFormRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.CUSTOMIZED_FORM_IS_NULL));
    if (!StringUtils.isEmpty(customizedEmployeeForm.getCode())) {
        throw new BizException(ResultCode.CAN_NOT_DELETE_BASIC_FORM);
    }
    customizedEmployeeFormRepository.deleteById(id);
    customizedFieldRespository.deleteCustomizedFieldByTargetId(id);
}


public void buildCustomizedForms(CustomizedFormsRequest customizedFormsRequest,CustomizedEmployeeForm customizedEmployeeForm,Boolean isUpdated){
    if (isUpdated) {
        copyProperties(customizedFormsRequest, customizedEmployeeForm, "idx", "code");
    } else {
        copyProperties(customizedFormsRequest, customizedEmployeeForm, "code");
    }
    // 创建表单项
    customizedEmployeeForm = customizedEmployeeFormRepository.save(customizedEmployeeForm);
    List<CustomizedFormsFieldsRequest> fields = customizedFormsRequest.getFields();
    List<CustomizedField> customizedFieldList = new ArrayList<>();
    Long customizedEmployeeFormId = customizedEmployeeForm.getId();
    if (!CollectionUtils.isEmpty(fields)) {
        for (int i = 0; i < fields.size(); i++) {
            CustomizedFormsFieldsRequest field = fields.get(i);
            CustomizedField customizedField = new CustomizedField();
            if (isUpdated) {
                customizedField.setId(field.getId());
            }
            customizedField.setFieldType(field.getFieldType());
            customizedField.setTargetId(customizedEmployeeFormId);
            customizedField.setEnable(YesNo.YES);
            customizedField.setIdx(i + 1);
            customizedField.setTargetType(TargetType.employee_form);
            customizedField.setAttribute(JSONObject.toJSONString(field.getAttribute()));
            customizedFieldList.add(customizedField);
        }
        customizedFieldRespository.saveAll(customizedFieldList);
    }
}


@Transactional
public void updateCustomizedForms(Long id,CustomizedFormsRequest customizedFormsRequest){
    CustomizedEmployeeForm customizedEmployeeForm = customizedEmployeeFormRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.CUSTOMIZED_FORM_IS_NULL));
    // 增加删除的逻辑
    List<CustomizedFormsFieldsRequest> fieldsList = customizedFormsRequest.getFields();
    if (fieldsList.isEmpty()) {
        customizedFieldRespository.deleteCustomizedFieldByTargetId(customizedEmployeeForm.getId());
    } else {
        List<Long> deleteIds = fieldsList.stream().filter(field -> field.getId() != null).map(CustomizedFormsFieldsRequest::getId).collect(toList());
        if (CollectionUtils.isEmpty(deleteIds)) {
            customizedFieldRespository.deleteCustomizedFieldByTargetId(customizedEmployeeForm.getId());
        } else {
            customizedFieldRespository.deleteNotExistenceByIds(customizedEmployeeForm.getId(), deleteIds);
        }
    }
    // 不覆盖类型
    customizedFormsRequest.setType(customizedEmployeeForm.getType());
    buildCustomizedForms(customizedFormsRequest, customizedEmployeeForm, TRUE);
}


public CustomizedFormLayoutResponse getCustomizedFormLayout(){
    List<CustomizedEmployeeForm> employeeForms = customizedEmployeeFormRepository.findByEnableOrderByTypeAscIdxAsc(TRUE);
    List<CustomizedFormResponse> positionForms = this.groupCustomizedForm(employeeForms, FormType.position);
    List<CustomizedFormResponse> basicForms = this.groupCustomizedForm(employeeForms, FormType.basic);
    List<CustomizedFormResponse> otherForms = this.groupCustomizedForm(employeeForms, FormType.other);
    return new CustomizedFormLayoutResponse(positionForms, basicForms, otherForms);
}


@Transactional
public void createCustomizedForms(CustomizedFormsRequest customizedFormsRequest){
    CustomizedEmployeeForm customizedEmployeeForm = new CustomizedEmployeeForm();
    customizedEmployeeForm.setEnable(TRUE);
    customizedEmployeeForm.setSwitchable(TRUE);
    customizedEmployeeForm.setCreatedBy(TenantThreadHelper.getToken().getId());
    Integer maxIdx = customizedEmployeeFormRepository.findMaxIdxByType(customizedFormsRequest.getType().name());
    customizedFormsRequest.setIdx(maxIdx + 1);
    buildCustomizedForms(customizedFormsRequest, customizedEmployeeForm, FALSE);
}


public List<CustomizedEmployeeFormDTO> convertFormListToDTOList(List<CustomizedEmployeeForm> forms){
    return forms.stream().map(form -> {
        CustomizedEmployeeFormDTO formDTO = new CustomizedEmployeeFormDTO();
        copyProperties(form, formDTO);
        boolean deletable = StringUtils.isEmpty(form.getCode());
        formDTO.setDeletable(deletable);
        return formDTO;
    }).collect(toList());
}


public List<CustomizedFormResponse> groupCustomizedForm(List<CustomizedEmployeeForm> customizedEmployeeForms,FormType formType){
    List<CustomizedFormResponse> customizedFormResponses = Lists.newArrayList();
    for (CustomizedEmployeeForm employeeForm : customizedEmployeeForms) {
        if (formType == employeeForm.getType()) {
            CustomizedFormResponse customizedFormResponse = new CustomizedFormResponse();
            copyProperties(employeeForm, customizedFormResponse);
            customizedFormResponses.add(customizedFormResponse);
        }
    }
    List<Long> ids = customizedFormResponses.stream().map(CustomizedFormResponse::getId).collect(toList());
    List<FormFieldDTO> formFieldDTOS = customizedFieldService.findByTargetTypeAndTargetIds(TargetType.employee_form, ids, entity -> {
        FormFieldDTO formFieldDTO = new FormFieldDTO();
        formFieldDTO.setId(entity.getId());
        formFieldDTO.setFormId(entity.getTargetId());
        formFieldDTO.setFieldType(entity.getFieldType());
        formFieldDTO.setAttribute(JSONObject.parseObject(entity.getAttribute()));
        return formFieldDTO;
    });
    if (!CollectionUtils.isEmpty(formFieldDTOS)) {
        Map<Long, List<FormFieldDTO>> formFieldMap = formFieldDTOS.stream().collect(groupingBy(FormFieldDTO::getFormId));
        for (CustomizedFormResponse customizedFormResponse : customizedFormResponses) {
            customizedFormResponse.setFields(formFieldMap.get(customizedFormResponse.getId()));
        }
    }
    return customizedFormResponses;
}


}