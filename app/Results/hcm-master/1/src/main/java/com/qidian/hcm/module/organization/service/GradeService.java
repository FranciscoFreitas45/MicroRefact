package com.qidian.hcm.module.organization.service;
 import com.alibaba.fastjson.JSON;
import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.framework.CommonRepositoryUtil;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.organization.dto.GradeDTO;
import com.qidian.hcm.module.organization.entity.GradeEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import com.qidian.hcm.module.organization.repository.GradeRepository;
import com.qidian.hcm.module.organization.repository.PositionRepository;
import com.qidian.hcm.module.organization.request.CreateGradeRequest;
import com.qidian.hcm.module.organization.request.EnableGradeRequest;
import com.qidian.hcm.module.organization.request.UpdateGradeRequest;
import com.qidian.hcm.module.organization.response.GradeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util;
import java.util.stream.Collectors;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
import com.qidian.hcm.common.utils.ResultCode.DEACTIVATE_OR_DELETE_POSITION_USED_BY_GRADE_FIRST;
import com.qidian.hcm.common.utils.ResultCode.GRADE_NOT_EXISTS;
@Service
public class GradeService extends CommonRepositoryUtil{

@Autowired
 private  GradeRepository gradeRepository;

@Autowired
 private  PositionRepository positionRepository;


@Transactional
public void deleteGradeById(Long id){
    checkPositionUsage(id);
    gradeRepository.updateStatus(SystemConstant.YES_INTEGER, id);
}


public void updateGrade(UpdateGradeRequest updateGradeRequest){
    GradeEntity gradeEntity = gradeRepository.findById(updateGradeRequest.getId()).orElseThrow(() -> new BizException(GRADE_NOT_EXISTS));
    BeanUtils.copyProperties(updateGradeRequest, gradeEntity);
    if (updateGradeRequest.getCustomField() != null) {
        gradeEntity.setCustomField(updateGradeRequest.getCustomField().toJSONString());
    }
    gradeRepository.save(gradeEntity);
}


public void checkPositionUsage(Long id){
    gradeRepository.findById(id).orElseThrow(() -> new BizException(GRADE_NOT_EXISTS));
    List<PositionEntity> positionEntityList = positionRepository.findByGradeIdAndEnableAndDeleted(id, YesNo.YES, YesNo.NO);
    if (!positionEntityList.isEmpty()) {
        throw new BizException(DEACTIVATE_OR_DELETE_POSITION_USED_BY_GRADE_FIRST);
    }
}


public void createGrade(CreateGradeRequest request){
    Optional<GradeEntity> gradeEntityOptional = gradeRepository.findByCode(request.getCode());
    if (gradeEntityOptional.isPresent()) {
        throw new BizException(ResultCode.GRADE_ALREADY_EXISTS);
    }
    GradeEntity gradeEntity = new GradeEntity();
    BeanUtils.copyProperties(request, gradeEntity);
    gradeEntity.setDelete(YesNo.NO);
    gradeEntity.setEnable(YesNo.YES);
    if (request.getCustomField() != null) {
        gradeEntity.setCustomField(request.getCustomField().toJSONString());
    }
    gradeRepository.save(gradeEntity);
}


@Transactional
public void enableGradeById(EnableGradeRequest request){
    YesNo enable = request.getEnable();
    List<Long> ids = request.getId();
    for (long id : ids) {
        if (enable == YesNo.NO) {
            checkPositionUsage(id);
        }
        gradeRepository.enableGrade(enable, id, new Date());
    }
}


public GradeDTO convertGradeEntityToResponse(GradeEntity gradeEntity){
    GradeDTO gradeDTO = new GradeDTO();
    BeanUtils.copyProperties(gradeEntity, gradeDTO);
    gradeDTO.setCustomField(gradeEntity.getCustomField());
    return gradeDTO;
}


public GradeDTO getGradeById(Long id){
    GradeEntity gradeEntity = gradeRepository.findById(id).orElseThrow(() -> new BizException(GRADE_NOT_EXISTS));
    return convertGradeEntityToResponse(gradeEntity);
}


public Page<GradeResponse> getGradeList(Integer active,String keyword,Integer pageNo,Integer pageSize){
    Map<String, Object> map = new HashMap<>();
    String select = "SELECT id,name,code,alias,rank,enable_time,deleted,enable,custom_field FROM grade  " + "WHERE deleted=0 and enable=:enable ";
    if (!StringUtils.isEmpty(keyword)) {
        select += "and (grade.id like :condition or grade.name like :condition or grade.code like :condition  " + "or grade.rank like :condition or grade.custom_field like :condition )";
        map.put("condition", "%" + keyword + "%");
    }
    map.put("enable", active);
    Page<GradeDTO> gradeDTOS = this.pageByNative(GradeDTO.class, select, null, map, pageNo, pageSize);
    List<GradeResponse> gradeResponses = newArrayListWithExpectedSize(gradeDTOS.getContent().size());
    for (GradeDTO gradeDTO : gradeDTOS.getContent()) {
        GradeResponse gradeResponse = new GradeResponse();
        BeanUtils.copyProperties(gradeDTO, gradeResponse);
        gradeResponse.setCustomField(JSON.parseObject(gradeDTO.getCustomField()));
        gradeResponses.add(gradeResponse);
    }
    return new PageImpl<>(gradeResponses, gradeDTOS.getPageable(), gradeDTOS.getTotalElements());
}


public Map<Long,String> getIdNameMap(){
    List<GradeEntity> grades = gradeRepository.findAll();
    return grades.stream().collect(Collectors.toMap(GradeEntity::getId, GradeEntity::getName));
}


}