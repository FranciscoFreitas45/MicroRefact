package com.qidian.hcm.module.organization.service;
 import com.alibaba.fastjson.JSON;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.PlatformType;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import com.qidian.hcm.module.organization.enums.OrganizationEnums;
import com.qidian.hcm.module.organization.repository.PositionRepository;
import com.qidian.hcm.module.organization.request.CreateDepartmentRequest;
import com.qidian.hcm.module.organization.request.UpdateDepartmentRequest;
import com.qidian.hcm.module.organization.response.DepartmentResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util;
import java.util.stream.Collectors;
import com.qidian.hcm.Interface.PositionRepository;
@Service
public class DepartmentService extends OrganizationService{

@Autowired
 private  PositionRepository positionRepository;


public void setEditStatus(List<DepartmentResponse> departmentResponses){
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        departmentResponses.forEach(resp -> resp.setEnableEdit(true));
    } else {
        List<Long> orgIds = listAllPermissionOrgIds(PlatformType.backend, MenuCode.department, ActionType.EDIT);
        departmentResponses.forEach(resp -> resp.setEnableEdit(orgIds.contains(resp.getId())));
    }
}


@Transactional
public void enableById(List<Long> ids,YesNo enable){
    if (YesNo.NO == enable) {
        // 如果岗位有在使用部门，则不允许禁用
        List<PositionEntity> positionEntities = positionRepository.findAllByDepartmentIdInAndEnableAndDeleted(ids, YesNo.YES, YesNo.NO);
        if (!positionEntities.isEmpty()) {
            throw new BizException(ResultCode.DEPARTMENT_IS_USED);
        }
        // 如果子部门有在使用父部门，则不允许禁用
        List<OrganizationEntity> children = organizationRepository.findAllByParentIdInAndEnableAndDeleted(ids, YesNo.YES, YesNo.NO);
        if (!children.isEmpty()) {
            throw new BizException(ResultCode.DEPARTMENT_IS_USED);
        }
    } else {
        Set<Long> parentIds = organizationRepository.findAllById(ids).stream().map(OrganizationEntity::getParentId).collect(Collectors.toSet());
        List<OrganizationEntity> parentDepartments = organizationRepository.findAllByIdInAndEnableAndDeleted(parentIds, YesNo.YES, YesNo.NO);
        if (parentIds.size() != parentDepartments.size()) {
            throw new BizException(ResultCode.PARENT_ORG_IS_DISABLED);
        }
    }
    List<OrganizationEntity> departments = organizationRepository.findAllById(ids);
    Date now = new Date();
    for (OrganizationEntity department : departments) {
        department.setEnable(enable);
        department.setEnableTime(now);
    }
    organizationRepository.saveAll(departments);
}


public void validateParent(Long parentId){
    getByIdOrElseThrow(parentId, ResultCode.PARENT_ORG_IS_NULL);
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        return;
    }
    List<Long> orgIds = listAllPermissionOrgIds(PlatformType.backend, MenuCode.organization, ActionType.EDIT);
    if (!orgIds.contains(parentId)) {
        throw new BizException(ResultCode.DO_NOT_HAVE_ACCESS);
    }
}


public void updateDepartment(UpdateDepartmentRequest request){
    validateParent(request.getParentId());
    validateOrgCode(request.getId(), request.getCode(), OrganizationEnums.department);
    OrganizationEntity organizationEntity = getByIdOrElseThrow(request.getId(), ResultCode.DEPARTMENT_IS_NULL);
    BeanUtils.copyProperties(request, organizationEntity);
    organizationEntity.setParentId(request.getParentId());
    // 检查部门信息是否循环依赖
    departmentIsCycleUp(request.getId(), organizationEntity);
    if (request.getCustomField() != null) {
        organizationEntity.setCustomField(request.getCustomField().toJSONString());
    }
    organizationRepository.save(organizationEntity);
    updateOrganizationPath(organizationEntity);
}


public DepartmentResponse convertToDepartmentResponse(OrganizationEntity department,List<OrganizationEntity> organizations){
    DepartmentResponse departmentResponse = new DepartmentResponse();
    BeanUtils.copyProperties(department, departmentResponse);
    departmentResponse.setCustomField(JSON.parseObject(department.getCustomField()));
    OrganizationEntity upperOrganization = findUpperOrganization(department, organizations);
    if (OrganizationEnums.department == upperOrganization.getType()) {
        departmentResponse.setParentDepartmentId(upperOrganization.getId());
        departmentResponse.setParentDepartmentName(upperOrganization.getName());
        OrganizationEntity upperCompany = findUpperCompany(upperOrganization, organizations);
        departmentResponse.setParentCompanyId(upperCompany.getId());
        departmentResponse.setParentCompanyName(upperCompany.getName());
    } else {
        departmentResponse.setParentCompanyId(upperOrganization.getId());
        departmentResponse.setParentCompanyName(upperOrganization.getName());
    }
    return departmentResponse;
}


public void createDepartment(CreateDepartmentRequest request){
    validateParent(request.getParentId());
    validateOrgCode(null, request.getCode(), OrganizationEnums.department);
    OrganizationEntity departmentEntity = new OrganizationEntity();
    BeanUtils.copyProperties(request, departmentEntity);
    departmentEntity.setParentId(request.getParentId());
    if (request.getCustomField() != null) {
        departmentEntity.setCustomField(request.getCustomField().toJSONString());
    }
    departmentEntity.setDeleted(YesNo.NO);
    departmentEntity.setType(OrganizationEnums.department);
    organizationRepository.save(departmentEntity);
    createOrganizationPath(departmentEntity);
}


public DepartmentResponse convertDepartmentEntityToResponse(OrganizationEntity departmentEntity){
    DepartmentResponse departmentResponse = new DepartmentResponse();
    BeanUtils.copyProperties(departmentEntity, departmentResponse);
    departmentResponse.setCustomField(JSON.parseObject(departmentEntity.getCustomField()));
    Optional<OrganizationEntity> parentDepartmentEntity = organizationRepository.findById(departmentEntity.getParentId());
    parentDepartmentEntity.ifPresent(departmentEntity1 -> {
        if (OrganizationEnums.company == departmentEntity1.getType()) {
            departmentResponse.setParentCompanyName(departmentEntity1.getName());
            departmentResponse.setParentCompanyId(departmentEntity.getParentId());
        } else {
            departmentResponse.setParentDepartmentName(departmentEntity1.getName());
            departmentResponse.setParentDepartmentId(departmentEntity.getParentId());
        }
    });
    return departmentResponse;
}


public DepartmentResponse getDepartmentById(Long id){
    OrganizationEntity departmentEntity = getByIdOrElseThrow(id, ResultCode.DEPARTMENT_IS_NULL);
    return convertDepartmentEntityToResponse(departmentEntity);
}


@Transactional
public void deleteById(Long id){
    OrganizationEntity organizationEntity = getByIdOrElseThrow(id, ResultCode.DEPARTMENT_IS_NULL);
    if (YesNo.YES == organizationEntity.getDeleted()) {
        throw new BizException(ResultCode.DEPARTMENT_IS_NULL);
    }
    List<PositionEntity> positions = positionRepository.findAllByDepartmentIdAndEnableAndDeleted(id, YesNo.YES, YesNo.NO);
    if (!positions.isEmpty()) {
        throw new BizException(ResultCode.DEPARTMENT_IS_USED);
    }
    List<OrganizationEntity> organizationEntities = organizationRepository.findAllByParentIdAndEnableAndDeleted(id, YesNo.YES, YesNo.NO);
    if (!organizationEntities.isEmpty()) {
        throw new BizException(ResultCode.DEPARTMENT_IS_USED);
    }
    organizationEntity.setDeleted(YesNo.YES);
    organizationRepository.save(organizationEntity);
}


public OrganizationEntity findUpperOrganization(OrganizationEntity department,List<OrganizationEntity> organizations){
    OrganizationEntity parent = null;
    for (OrganizationEntity org : organizations) {
        if (department.getParentId().equals(org.getId())) {
            parent = org;
            break;
        }
    }
    return parent;
}


public void departmentIsCycleUp(Long departmentId,OrganizationEntity departmentEntity){
    OrganizationEntity parentOrgEntity = getByIdOrElseThrow(departmentEntity.getParentId(), ResultCode.PARENT_ORG_IS_NULL);
    if (departmentId.equals(parentOrgEntity.getId())) {
        throw new BizException(ResultCode.DEPARTMENT_IS_CYCLE);
    }
    if (parentOrgEntity.getParentId() > 0) {
        departmentIsCycleUp(departmentId, parentOrgEntity);
    }
}


public Page<DepartmentResponse> getDepartments(Integer active,String keyword,Integer pageNo,Integer pageSize){
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.desc("id")));
    Specification<OrganizationEntity> filterParam = (root, query, criteriaBuilder) -> {
        Predicate enableEq = criteriaBuilder.equal(root.get("enable"), active);
        Predicate deleteEq = criteriaBuilder.equal(root.get("deleted"), YesNo.NO);
        Predicate typeEq = criteriaBuilder.equal(root.get("type"), OrganizationEnums.department);
        return criteriaBuilder.and(enableEq, typeEq, deleteEq);
    };
    Specification<OrganizationEntity> condition = Specification.where(filterParam);
    if (!StringUtils.isEmpty(keyword)) {
        Specification<OrganizationEntity> keywordLike = (root, query, criteriaBuilder) -> {
            Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
            Predicate aliasLike = criteriaBuilder.like(root.get("alias"), "%" + keyword + "%");
            return criteriaBuilder.or(nameLike, aliasLike);
        };
        condition = condition.and(keywordLike);
    }
    // 数据权限过滤
    if (!TenantThreadHelper.getToken().isSuperAdmin()) {
        List<Long> orgIds = TenantThreadHelper.getPermissionOrgIds(PlatformType.backend, MenuCode.department, ActionType.GET);
        Specification<OrganizationEntity> orgPathLike = getOrgPathLikeSpecification(orgIds);
        condition = condition.and(orgPathLike);
    }
    Page<OrganizationEntity> pageBean = organizationRepository.findAll(condition, pageable);
    List<OrganizationEntity> organizations = organizationRepository.findAllByDeleted(YesNo.NO);
    List<DepartmentResponse> departmentResponses = new ArrayList<>(pageBean.getContent().size());
    for (OrganizationEntity department : pageBean.getContent()) {
        DepartmentResponse departmentResponse = convertToDepartmentResponse(department, organizations);
        departmentResponses.add(departmentResponse);
    }
    setEditStatus(departmentResponses);
    return new PageImpl<>(departmentResponses, pageable, pageBean.getTotalElements());
}


public OrganizationEntity findUpperCompany(OrganizationEntity department,List<OrganizationEntity> organizations){
    OrganizationEntity parent = findUpperOrganization(department, organizations);
    if (parent.getType() == OrganizationEnums.department) {
        parent = findUpperCompany(parent, organizations);
    }
    return parent;
}


}