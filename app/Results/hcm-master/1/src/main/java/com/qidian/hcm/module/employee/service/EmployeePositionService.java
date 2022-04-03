package com.qidian.hcm.module.employee.service;
 import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qidian.hcm.module.center.service.FileService;
import com.qidian.hcm.module.custom.entity.CustomizedField;
import com.qidian.hcm.module.custom.enums.FieldType;
import com.qidian.hcm.module.custom.service.CustomizedFieldService;
import com.qidian.hcm.module.employee.dto.EmployeePositionDTO;
import com.qidian.hcm.module.employee.entity.Attachment;
import com.qidian.hcm.module.employee.entity.EmployeePosition;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.repository.EmployeePositionRepository;
import com.qidian.hcm.module.employee.request.TransferEmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
import com.qidian.hcm.Interface.CustomizedFieldService;
import com.qidian.hcm.Interface.FileService;
@Service
@Slf4j
public class EmployeePositionService {

@Autowired
 private  EmployeePositionRepository employeePositionRepository;

@Autowired
 private  CustomizedFieldService customizedFieldService;

@Autowired
 private  FileService fileService;


public void deleteAllByEmployeeId(Long employeeId){
    employeePositionRepository.deleteAllByEmployeeId(employeeId);
}


public List<EmployeePosition> findAllByExcludeEmployeeStatus(EmployeeStatus status){
    return employeePositionRepository.findAllByExcludeEmployeeStatus(status);
}


public List<EmployeePosition> findAllByCondition(Specification<EmployeePosition> condition){
    return employeePositionRepository.findAll(condition);
}


public void updateLeaderId(Long employeeId,Long handoverManId){
    employeePositionRepository.updateLeaderId(employeeId, handoverManId);
}


public EmployeePosition getCurrentEmployeePosition(Long employeeId){
    List<Long> employeeIds = Lists.newArrayList(employeeId);
    Map<Long, EmployeePosition> currentPositionMap = getEmployeeCurrentPositionMap(employeeIds);
    return currentPositionMap.get(employeeId);
}


public List<EmployeePositionDTO> getEmployeePositionDTOList(Long employeeId){
    List<EmployeePosition> employeePositionList = employeePositionRepository.findAllByEmployeeIdOrderByStartDateDesc(employeeId);
    List<EmployeePositionDTO> positions = newArrayListWithExpectedSize(employeePositionList.size());
    for (EmployeePosition employeePosition : employeePositionList) {
        EmployeePositionDTO employeePositionDTO = new EmployeePositionDTO();
        employeePosition.setEmployeeId(employeeId);
        BeanUtils.copyProperties(employeePosition, employeePositionDTO);
        employeePositionDTO.setCustomizedFields(JSONObject.parseObject(employeePosition.getCustomizedField()));
        positions.add(employeePositionDTO);
    }
    return positions;
}


public void addEmployeePositionForTransferredEmployee(Long employeeId,TransferEmployeeRequest transferEmployeeRequest){
    EmployeePosition employeePosition = new EmployeePosition();
    employeePosition.setEmployeeId(employeeId);
    employeePosition.setCompanyId(transferEmployeeRequest.getCompanyId());
    employeePosition.setDepartmentId(transferEmployeeRequest.getDepartmentId());
    employeePosition.setPositionId(transferEmployeeRequest.getPositionId());
    employeePosition.setLeaderId(transferEmployeeRequest.getMasterId());
    employeePosition.setStartDate(transferEmployeeRequest.getDate());
    employeePositionRepository.save(employeePosition);
}


public void saveEmployeePositions(Long employeeId,List<EmployeePositionDTO> employeePositionDTOList){
    employeePositionRepository.deleteAllByEmployeeId(employeeId);
    List<EmployeePosition> employeePositionList = newArrayListWithExpectedSize(employeePositionDTOList.size());
    for (EmployeePositionDTO position : employeePositionDTOList) {
        EmployeePosition employeePosition = new EmployeePosition();
        employeePosition.setEmployeeId(employeeId);
        BeanUtils.copyProperties(position, employeePosition);
        JSONObject customizedFields = position.getCustomizedFields();
        JSONObject newCustomizedFields = new JSONObject();
        if (customizedFields != null && !customizedFields.isEmpty()) {
            Set<Map.Entry<String, Object>> entrySet = customizedFields.entrySet();
            for (Map.Entry<String, Object> objectEntry : entrySet) {
                CustomizedField cf = customizedFieldService.getById(Long.valueOf(objectEntry.getKey()));
                if (cf != null && FieldType.file == cf.getFieldType()) {
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(objectEntry.getValue()));
                    String data = jsonObject.getString("data");
                    String fileName = jsonObject.getString("fileName");
                    Long fileId;
                    if (StringUtils.isEmpty(data)) {
                        fileId = Long.valueOf(jsonObject.getString("fileId"));
                    } else {
                        Attachment attachment = fileService.uploadFileToOSS(data, fileName);
                        fileId = attachment.getFileId();
                    }
                    JSONObject tempJSON = new JSONObject();
                    tempJSON.put("fileId", fileId);
                    tempJSON.put("fileName", fileName);
                    newCustomizedFields.put(objectEntry.getKey(), tempJSON);
                } else {
                    newCustomizedFields.put(objectEntry.getKey(), objectEntry.getValue());
                }
            }
            employeePosition.setCustomizedField(JSONObject.toJSONString(newCustomizedFields));
        }
        employeePositionList.add(employeePosition);
    }
    employeePositionRepository.saveAll(employeePositionList);
}


public EmployeePosition findCurrentPosition(List<EmployeePosition> employeePositions){
    employeePositions.sort((a, b) -> {
        Date date1 = a.getStartDate();
        Date date2 = b.getStartDate();
        return date2.compareTo(date1);
    });
    Date now = new Date();
    for (EmployeePosition ep : employeePositions) {
        if (ep.getStartDate().compareTo(now) <= 0) {
            return ep;
        }
    }
    return null;
}


public Map<Long,EmployeePosition> getEmployeeCurrentPositionMap(List<Long> employeeIds){
    Map<Long, List<EmployeePosition>> employeePositionsMap = employeePositionRepository.findAllByEmployeeIdIn(employeeIds).stream().collect(Collectors.groupingBy(EmployeePosition::getEmployeeId));
    Map<Long, EmployeePosition> map = Maps.newHashMapWithExpectedSize(employeePositionsMap.size());
    for (Map.Entry<Long, List<EmployeePosition>> entry : employeePositionsMap.entrySet()) {
        EmployeePosition currentPosition = findCurrentPosition(entry.getValue());
        map.put(entry.getKey(), currentPosition);
    }
    return map;
}


}