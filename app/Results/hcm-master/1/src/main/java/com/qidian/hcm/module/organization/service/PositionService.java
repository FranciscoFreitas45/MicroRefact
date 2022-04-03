package com.qidian.hcm.module.organization.service;
 import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.framework.CommonRepositoryUtil;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.PlatformType;
import com.qidian.hcm.module.employee.entity.EmployeePosition;
import com.qidian.hcm.module.employee.repository.EmployeePositionRepository;
import com.qidian.hcm.module.organization.dto.PositionGradeDTO;
import com.qidian.hcm.module.organization.entity.GradeEntity;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import com.qidian.hcm.module.organization.repository.GradeRepository;
import com.qidian.hcm.module.organization.repository.OrganizationRepository;
import com.qidian.hcm.module.organization.repository.PositionRepository;
import com.qidian.hcm.module.organization.request.CreatePositionRequest;
import com.qidian.hcm.module.organization.request.EnablePositionRequest;
import com.qidian.hcm.module.organization.request.UpdatePositionRequest;
import com.qidian.hcm.module.organization.response.PositionResponse;
import com.qidian.hcm.module.organization.response.PositionTreeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util;
import java.util.stream.Collectors;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.Interface.OrganizationRepository;
import com.qidian.hcm.Interface.OrganizationService;
@Service
public class PositionService extends CommonRepositoryUtil{

@Autowired
 private  PositionRepository positionRepository;

@Autowired
 private  OrganizationRepository organizationRepository;

@Autowired
 private  GradeRepository gradeRepository;

@Autowired
 private  EmployeePositionRepository employeePositionRepository;

@Autowired
 private  OrganizationService organizationService;

@Autowired
 private  GradeService gradeService;


public void setParentPositionNames(List<PositionResponse> positionResponses){
    Map<Long, String> positionIdNameMap = getIdNameMap();
    for (PositionResponse positionResponse : positionResponses) {
        if (positionResponse.getParentPositionId() != null) {
            positionResponse.setParentPositionName(positionIdNameMap.get(positionResponse.getParentPositionId()));
        }
    }
}


public PositionResponse getPositionById(Long id){
    PositionEntity position = getById(id);
    return convertPositionEntityToResponse(position);
}


public void setEditStatus(List<PositionResponse> positionResponses){
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        positionResponses.forEach(resp -> resp.setEnableEdit(true));
    } else {
        List<Long> orgIds = organizationService.listAllPermissionOrgIds(PlatformType.backend, MenuCode.position, ActionType.EDIT);
        positionResponses.forEach(resp -> resp.setEnableEdit(orgIds.contains(resp.getId())));
    }
}


public PositionResponse convertPositionEntityToResponse(PositionEntity positionEntity){
    PositionResponse positionResponse = new PositionResponse();
    BeanUtils.copyProperties(positionEntity, positionResponse);
    positionResponse.setCustomField(JSON.parseObject(positionEntity.getCustomField()));
    Optional<OrganizationEntity> departmentEntity = organizationRepository.findById(positionResponse.getDepartmentId());
    departmentEntity.ifPresent(departmentEntity1 -> positionResponse.setDepartmentName(departmentEntity1.getName()));
    departmentEntity.ifPresent(departmentEntity1 -> positionResponse.setMaster(departmentEntity1.getMaster()));
    if (null != positionResponse.getParentPositionId()) {
        Optional<PositionEntity> parentPosition = positionRepository.findById(positionResponse.getParentPositionId());
        parentPosition.ifPresent(positionEntity1 -> positionResponse.setParentPositionName(positionEntity1.getName()));
    }
    Optional<GradeEntity> gradeEntity = gradeRepository.findById(positionResponse.getGradeId());
    gradeEntity.ifPresent(gradeEntity1 -> positionResponse.setGradeName(gradeEntity1.getName()));
    return positionResponse;
}


public List<PositionTreeResponse> getPositionTree(){
    List<PositionTreeResponse> positionTreeResponseList = positionRepository.findPositionTree();
    List<PositionTreeResponse> rootPositionList = positionRepository.findRootPosition();
    for (PositionTreeResponse rootPosition : rootPositionList) {
        addChildrenList(positionTreeResponseList, rootPosition);
        rootPosition.setUid(UUID.randomUUID().toString());
    }
    return rootPositionList;
}


public Map<Long,PositionEntity> getIdEntityMap(){
    List<PositionEntity> positions = positionRepository.findAll();
    return positions.stream().collect(Collectors.toMap(PositionEntity::getId, positionEntity -> positionEntity));
}


public void createPosition(CreatePositionRequest request){
    Optional<PositionEntity> positionOptional = positionRepository.findByCode(request.getCode());
    if (positionOptional.isPresent()) {
        throw new BizException(POSITION_ALREADY_EXISTS);
    }
    PositionEntity positionEntity = new PositionEntity();
    BeanUtils.copyProperties(request, positionEntity);
    if (request.getCustomField() != null) {
        positionEntity.setCustomField(request.getCustomField().toJSONString());
    }
    positionEntity.setDeleted(YesNo.NO);
    positionEntity.setEnable(YesNo.YES);
    positionRepository.save(positionEntity);
}


public Page<PositionResponse> getPositionList(Integer active,String keyword,Integer pageNo,Integer pageSize){
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.desc("id")));
    Specification<PositionEntity> specification = (root, query, cb) -> {
        List<Predicate> predicates = Lists.newArrayList();
        predicates.add(cb.equal(root.get("enable"), active));
        predicates.add(cb.equal(root.get("deleted"), YesNo.NO));
        if (!StringUtils.isEmpty(keyword)) {
            List<Predicate> keywordPredicates = Lists.newArrayListWithExpectedSize(2);
            keywordPredicates.add(cb.like(root.get("name"), "%" + keyword + "%"));
            keywordPredicates.add(cb.like(root.get("alias"), "%" + keyword + "%"));
            Predicate[] keywordPredicateArr = new Predicate[keywordPredicates.size()];
            predicates.add(cb.or(keywordPredicates.toArray(keywordPredicateArr)));
        }
        // 数据权限过滤
        if (!TenantThreadHelper.getToken().isSuperAdmin()) {
            List<Long> permissionOrgIds = TenantThreadHelper.getPermissionOrgIds(PlatformType.backend, MenuCode.position, ActionType.GET);
            List<Long> selfAndChildrenIds = organizationService.listSelfAndChildrenIds(permissionOrgIds);
            if (!selfAndChildrenIds.isEmpty()) {
                CriteriaBuilder.In<Long> in = cb.in(root.get("departmentId"));
                selfAndChildrenIds.forEach(id -> in.value(id));
                predicates.add(in);
            }
        }
        Predicate[] arr = new Predicate[predicates.size()];
        return cb.and(predicates.toArray(arr));
    };
    Page<PositionEntity> pageBean = positionRepository.findAll(specification, pageable);
    List<PositionResponse> positionResponses = Lists.newArrayListWithExpectedSize(pageBean.getContent().size());
    if (!pageBean.getContent().isEmpty()) {
        for (PositionEntity position : pageBean.getContent()) {
            PositionResponse positionResponse = new PositionResponse();
            BeanUtils.copyProperties(position, positionResponse);
            positionResponse.setCustomField(JSON.parseObject(position.getCustomField()));
            positionResponses.add(positionResponse);
        }
        setDepartmentInfo(positionResponses);
        setParentPositionNames(positionResponses);
        setGradeNames(positionResponses);
        setEditStatus(positionResponses);
    }
    return new PageImpl<>(positionResponses, pageable, pageBean.getTotalElements());
}


public void setDepartmentInfo(List<PositionResponse> positionResponses){
    Map<Long, OrganizationEntity> orgIdEntityMap = organizationService.getIdEntityMap();
    for (PositionResponse positionResponse : positionResponses) {
        OrganizationEntity department = orgIdEntityMap.get(positionResponse.getDepartmentId());
        positionResponse.setDepartmentName(department.getName());
        positionResponse.setMaster(department.getMaster());
    }
}


public void setGradeNames(List<PositionResponse> positionResponses){
    Map<Long, String> gradeIdNameMap = gradeService.getIdNameMap();
    for (PositionResponse positionResponse : positionResponses) {
        positionResponse.setGradeName(gradeIdNameMap.get(positionResponse.getGradeId()));
    }
}


@Transactional
public void toggleActivePositions(EnablePositionRequest request){
    List<Long> ids = request.getId();
    YesNo enable = request.getEnable();
    if (enable == YesNo.NO) {
        validateChildPosition(ids);
        validatePositionUsedByEmployee(ids);
    } else if (enable == YesNo.YES) {
        checkEnableUsage(ids);
    }
    List<PositionEntity> positionEntities = positionRepository.findByIdInAndDeleted(ids, YesNo.NO);
    for (PositionEntity positionEntity : positionEntities) {
        positionEntity.setEnable(enable);
        if (enable == YesNo.YES) {
            positionEntity.setEnableTime(new Date());
        }
    }
    positionRepository.saveAll(positionEntities);
}


public Map<Long,String> getIdNameMap(){
    List<PositionEntity> positions = positionRepository.findAll();
    return positions.stream().collect(Collectors.toMap(PositionEntity::getId, PositionEntity::getName));
}


@Transactional
public void deletePositionById(Long id){
    validateChildPosition(Lists.newArrayList(id));
    Optional<PositionEntity> positionEntityOptional = positionRepository.findById(id);
    if (positionEntityOptional.isPresent()) {
        PositionEntity positionEntity = positionEntityOptional.get();
        positionEntity.setDeleted(YesNo.YES);
        positionRepository.save(positionEntity);
    } else {
        throw new BizException(POSITION_NOT_EXISTS);
    }
}


public void validateChildPosition(List<Long> ids){
    List<PositionEntity> positionEntityList = positionRepository.findByParentPositionIdInAndEnableAndDeleted(ids, YesNo.YES, YesNo.NO);
    if (!positionEntityList.isEmpty()) {
        throw new BizException(DEACTIVATE_CHILD_POSITION_FIRST);
    }
}


public void validatePositionUsedByEmployee(List<Long> ids){
    List<EmployeePosition> employeePositionList = employeePositionRepository.findAllByPositionIdIn(ids);
    if (!employeePositionList.isEmpty()) {
        throw new BizException(POSITION_IS_USED_BY_EMPLOYEE);
    }
}


public void updatePositionById(UpdatePositionRequest updatePositionRequest){
    PositionEntity positionEntity = getById(updatePositionRequest.getId());
    BeanUtils.copyProperties(updatePositionRequest, positionEntity);
    // 检查岗位信息是否循环依赖
    positionIsCycleUp(updatePositionRequest.getId(), positionEntity);
    if (updatePositionRequest.getCustomField() != null) {
        positionEntity.setCustomField(updatePositionRequest.getCustomField().toJSONString());
    }
    positionRepository.save(positionEntity);
}


public List<PositionGradeDTO> getPositionByDepartmentId(Long id){
    return positionRepository.getPositionByDepartmentId(id);
}


public List<PositionResponse> listPositions(Long departmentId){
    List<Long> departmentIds = organizationService.listSelfAndChildrenIds(Lists.newArrayList(departmentId));
    List<PositionEntity> positions = positionRepository.findAllByDepartmentIdInAndEnableAndDeleted(departmentIds, YesNo.YES, YesNo.NO);
    List<PositionResponse> list = Lists.newArrayListWithExpectedSize(positions.size());
    positions.forEach(position -> {
        PositionResponse response = new PositionResponse();
        BeanUtils.copyProperties(position, response);
    });
    return list;
}


public PositionEntity getById(Long id){
    return positionRepository.findById(id).orElseThrow(() -> new BizException(POSITION_NOT_EXISTS));
}


public void positionIsCycleUp(Long positionId,PositionEntity positionEntity){
    if (null != positionEntity.getParentPositionId()) {
        Optional<PositionEntity> parentPositionEntityOptional = positionRepository.findById(positionEntity.getParentPositionId());
        if (parentPositionEntityOptional.isPresent()) {
            PositionEntity parentPositionEntity = parentPositionEntityOptional.get();
            if (positionId.equals(parentPositionEntity.getId())) {
                throw new BizException(POSITION_IS_CYCLE);
            }
            positionIsCycleUp(positionId, parentPositionEntity);
        }
    }
}


public PositionTreeResponse addChildren(PositionTreeResponse rootPosition,PositionTreeResponse positionTreeResponse){
    Long parentPositionId = positionTreeResponse.getParentPositionId();
    if (rootPosition.getId().equals(parentPositionId)) {
        return positionTreeResponse;
    }
    return null;
}


public void checkEnableUsage(List<Long> ids){
    // 判断当前岗位是否存在
    List<PositionEntity> positionEntities = positionRepository.findByIdInAndDeleted(ids, YesNo.NO);
    if (positionEntities.size() != ids.size()) {
        throw new BizException(PARAM_INVALID);
    }
    // 上级岗位不能是失效状态
    List<PositionEntity> disabledParentPositions = positionRepository.findAllDisabledParentPositionByIdIn(ids);
    if (!disabledParentPositions.isEmpty()) {
        throw new BizException(ACTIVE_PARENT_POSITION_FIRST);
    }
    // 岗位关联的部门不能是失效状态
    List<OrganizationEntity> disabledDepartmentEntityList = organizationRepository.findAllDisabledDepartmentByPositionIdIn(ids);
    if (!disabledDepartmentEntityList.isEmpty()) {
        throw new BizException(ACTIVE_DEVELOPMENT_FIRST);
    }
    // 岗位关联的职级不能是失效状态
    List<GradeEntity> gradeEntityList = gradeRepository.findAllDisabledGradeByPositionIdIn(ids);
    if (!gradeEntityList.isEmpty()) {
        throw new BizException(ACTIVE_GRADE_FIRST);
    }
}


public void addChildrenList(List<PositionTreeResponse> positionTreeResponseList,PositionTreeResponse rootPosition){
    for (PositionTreeResponse positionTreeResponse : positionTreeResponseList) {
        PositionTreeResponse resultItem = addChildren(rootPosition, positionTreeResponse);
        if (resultItem != null) {
            resultItem.setUid(UUID.randomUUID().toString());
            rootPosition.addChildren(positionTreeResponse);
            addChildrenList(positionTreeResponseList, positionTreeResponse);
        }
    }
}


}