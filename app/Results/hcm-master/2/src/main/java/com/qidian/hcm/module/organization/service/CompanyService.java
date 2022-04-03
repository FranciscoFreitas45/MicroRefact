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
import com.qidian.hcm.module.organization.request.CreateCompanyRequest;
import com.qidian.hcm.module.organization.request.UpdateCompanyRequest;
import com.qidian.hcm.module.organization.response.CompanyResponse;
import com.qidian.hcm.module.organization.response.OrganizationTreeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util;
import java.util.stream.Collectors;
import com.qidian.hcm.module.organization.enums.OrganizationEnums.company;
@Slf4j
@Service
public class CompanyService extends OrganizationService{


public void validateParentCompanyExist(Long parentId){
    if (parentId == -1) {
        return;
    }
    getByIdOrElseThrow(parentId, ResultCode.PARENT_COMPANY_IS_NULL);
}


public void setEditStatus(List<CompanyResponse> companyResponses){
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        companyResponses.forEach(resp -> resp.setEnableEdit(true));
    } else {
        List<Long> orgIds = listAllPermissionOrgIds(PlatformType.backend, MenuCode.company, ActionType.EDIT);
        companyResponses.forEach(resp -> resp.setEnableEdit(orgIds.contains(resp.getId())));
    }
}


@Transactional
public void updateCompany(UpdateCompanyRequest updateCompanyRequest){
    validateCompanyPermission(updateCompanyRequest.getParentId());
    validateParentCompanyExist(updateCompanyRequest.getParentId());
    validateOrgCode(updateCompanyRequest.getId(), updateCompanyRequest.getCode(), company);
    OrganizationEntity organization = getByIdOrElseThrow(updateCompanyRequest.getId(), ResultCode.COMPANY_IS_NULL);
    BeanUtils.copyProperties(updateCompanyRequest, organization);
    companyIsCycleUp(updateCompanyRequest.getId(), organization);
    if (Objects.nonNull(updateCompanyRequest.getCustomField())) {
        organization.setCustomField(updateCompanyRequest.getCustomField().toJSONString());
    }
    organizationRepository.save(organization);
    updateOrganizationPath(organization);
}


public void enablePermissionOrganization(List<OrganizationTreeResponse> organizations,ActionType actionType){
    if (TenantThreadHelper.getToken().isSuperAdmin() || actionType == null) {
        organizations.forEach(organization -> organization.setEnabled(true));
    } else {
        List<Long> allPermissionOrgIds = listAllPermissionOrgIds(PlatformType.backend, MenuCode.organization, actionType);
        organizations.forEach(organization -> {
            if (allPermissionOrgIds.contains(organization.getId())) {
                organization.setEnabled(true);
            }
        });
    }
}


public CompanyResponse convertToCompanyResponse(OrganizationEntity company){
    CompanyResponse companyResponse = new CompanyResponse();
    BeanUtils.copyProperties(company, companyResponse);
    companyResponse.setCustomField(JSON.parseObject(company.getCustomField()));
    if (company.getParentId() > 0) {
        OrganizationEntity parentCompany = organizationRepository.findAllByDeleted(YesNo.NO).stream().filter(item -> item.getId().equals(company.getParentId())).collect(Collectors.toList()).get(0);
        companyResponse.setParentName(parentCompany.getName());
    }
    return companyResponse;
}


@Transactional
public void createCompany(CreateCompanyRequest request){
    // 数据权限过滤
    validateCompanyPermission(request.getParentId());
    validateParentCompanyExist(request.getParentId());
    validateOrgCode(null, request.getCode(), company);
    OrganizationEntity companyEntity = new OrganizationEntity();
    BeanUtils.copyProperties(request, companyEntity);
    if (request.getCustomField() != null) {
        companyEntity.setCustomField(request.getCustomField().toJSONString());
    }
    companyEntity.setDeleted(YesNo.NO);
    companyEntity.setEnable(YesNo.YES);
    companyEntity.setType(company);
    organizationRepository.save(companyEntity);
    createOrganizationPath(companyEntity);
}


public OrganizationTreeResponse getChildren(OrganizationTreeResponse rootCompany,OrganizationTreeResponse item){
    Long parentId = item.getParentId();
    // 当前节点是公司类型
    if (parentId.equals(rootCompany.getId())) {
        return item;
    }
    // 当前节点是部门类型
    return null;
}


public Page<CompanyResponse> getCompanies(Integer active,String keyword,Integer pageNo,Integer pageSize){
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
    Specification<OrganizationEntity> filterParam = (root, query, criteriaBuilder) -> {
        Predicate enableEq = criteriaBuilder.equal(root.get("enable"), active);
        Predicate deleteEq = criteriaBuilder.equal(root.get("deleted"), YesNo.NO);
        Predicate typeEq = criteriaBuilder.equal(root.get("type"), company);
        return criteriaBuilder.and(enableEq, typeEq, deleteEq);
    };
    Specification<OrganizationEntity> condition = Specification.where(filterParam);
    if (!StringUtils.isEmpty(keyword)) {
        Specification<OrganizationEntity> keywordLike = (root, query, criteriaBuilder) -> {
            Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
            Predicate aliasLike = criteriaBuilder.like(root.get("alias"), "%" + keyword + "%");
            Predicate regAddressLike = criteriaBuilder.like(root.get("registerAddress"), "%" + keyword + "%");
            Predicate masterLike = criteriaBuilder.like(root.get("master"), "%" + keyword + "%");
            return criteriaBuilder.or(nameLike, aliasLike, regAddressLike, masterLike);
        };
        condition = condition.and(keywordLike);
    }
    // 数据权限过滤
    if (!TenantThreadHelper.getToken().isSuperAdmin()) {
        List<Long> orgIds = TenantThreadHelper.getPermissionOrgIds(PlatformType.backend, MenuCode.company, ActionType.GET);
        Specification<OrganizationEntity> s3 = getOrgPathLikeSpecification(orgIds);
        condition = condition.and(s3);
    }
    Page<OrganizationEntity> pageBean = organizationRepository.findAll(condition, pageable);
    List<CompanyResponse> companyResponses = new ArrayList<>(pageBean.getContent().size());
    for (OrganizationEntity company : pageBean.getContent()) {
        CompanyResponse companyResponse = convertToCompanyResponse(company);
        companyResponses.add(companyResponse);
    }
    setEditStatus(companyResponses);
    return new PageImpl<>(companyResponses, pageable, pageBean.getTotalElements());
}


@Transactional
public void enableOrgById(List<Long> ids,YesNo enable){
    if (enable == YesNo.NO) {
        List<OrganizationEntity> childrenCompany = organizationRepository.findAllByParentIdInAndEnableAndDeleted(ids, YesNo.YES, YesNo.NO);
        if (!childrenCompany.isEmpty()) {
            throw new BizException(ResultCode.COMPANY_IS_USED);
        }
    } else {
        Set<Long> parentIds = organizationRepository.findAllById(ids).stream().map(OrganizationEntity::getParentId).collect(Collectors.toSet());
        List<OrganizationEntity> parentDepartments = organizationRepository.findAllByIdInAndEnableAndDeleted(parentIds, YesNo.YES, YesNo.NO);
        if (parentIds.size() != parentDepartments.size()) {
            throw new BizException(ResultCode.PARENT_ORG_IS_DISABLED);
        }
    }
    List<OrganizationEntity> companies = organizationRepository.findAllById(ids);
    for (OrganizationEntity company : companies) {
        company.setEnable(enable);
        company.setEnableTime(new Date());
    }
    organizationRepository.saveAll(companies);
}


public OrganizationTreeResponse getOrganizationTree(Boolean includeDepart,ActionType actionType){
    List<OrganizationTreeResponse> companyList = includeDepart ? organizationRepository.findCompanyTree() : organizationRepository.findCompanyOrDepartmentTree(company);
    // 数据权限过滤
    enablePermissionOrganization(companyList, actionType);
    setOrgTreeEditStatus(companyList);
    OrganizationTreeResponse rootCompany = companyList.get(0);
    addChildrenList(companyList, rootCompany);
    return rootCompany;
}


public CompanyResponse convertCompanyEntityToResponse(OrganizationEntity organizationEntity){
    CompanyResponse companyResponse = new CompanyResponse();
    BeanUtils.copyProperties(organizationEntity, companyResponse);
    companyResponse.setCustomField(JSON.parseObject(organizationEntity.getCustomField()));
    Optional<OrganizationEntity> parentCompanyEntity = organizationRepository.findById(companyResponse.getParentId());
    parentCompanyEntity.ifPresent(companyEntity1 -> companyResponse.setParentName(companyEntity1.getName()));
    return companyResponse;
}


public CompanyResponse getCompanyById(Long id){
    OrganizationEntity companyEntity = getByIdOrElseThrow(id, ResultCode.COMPANY_IS_NULL);
    return convertCompanyEntityToResponse(companyEntity);
}


@Transactional
public void deleteOrgById(Long id){
    OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.COMPANY_IS_NULL));
    if (organizationEntity.getParentId() < 0) {
        throw new BizException(ResultCode.ROOT_COMPANY_NOT_DELETE);
    }
    List<OrganizationEntity> organizationEntities = organizationRepository.findAllByParentIdAndEnable(id, YesNo.YES);
    if (!organizationEntities.isEmpty()) {
        throw new BizException(ResultCode.COMPANY_IS_USED);
    }
    organizationEntity.setDeleted(YesNo.YES);
    organizationRepository.save(organizationEntity);
}


public void setOrgTreeEditStatus(List<OrganizationTreeResponse> organizationTreeResponses){
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        organizationTreeResponses.forEach(resp -> resp.setEnableEdit(true));
    } else {
        List<Long> orgIds = listAllPermissionOrgIds(PlatformType.backend, MenuCode.organization, ActionType.EDIT);
        organizationTreeResponses.forEach(resp -> resp.setEnableEdit(orgIds.contains(resp.getId())));
    }
}


public void companyIsCycleUp(Long companyId,OrganizationEntity organizationEntity){
    Optional<OrganizationEntity> companyEntityOptional = organizationRepository.findById(organizationEntity.getParentId());
    if (companyEntityOptional.isPresent()) {
        OrganizationEntity parentCompanyEntity = companyEntityOptional.get();
        if (companyId.equals(parentCompanyEntity.getId())) {
            throw new BizException(ResultCode.COMPANY_IS_CYCLE);
        }
        companyIsCycleUp(companyId, parentCompanyEntity);
    }
}


public void validateCompanyPermission(Long companyId){
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        return;
    }
    List<Long> orgIds = listAllPermissionOrgIds(PlatformType.backend, MenuCode.company, ActionType.EDIT);
    if (!orgIds.contains(companyId)) {
        throw new BizException(ResultCode.DO_NOT_HAVE_ACCESS);
    }
}


public void addChildrenList(List<OrganizationTreeResponse> companyList,OrganizationTreeResponse rootCompany){
    for (OrganizationTreeResponse item : companyList) {
        OrganizationTreeResponse resultItem = getChildren(rootCompany, item);
        if (resultItem != null) {
            rootCompany.addChildren(item);
            addChildrenList(companyList, item);
        }
    }
}


}