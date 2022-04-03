package com.qidian.hcm.module.organization.service;
 import com.google.common.collect.Lists;
import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.PlatformType;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.enums.OrganizationEnums;
import com.qidian.hcm.module.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrganizationService {

@Autowired
 protected  OrganizationRepository organizationRepository;


public OrganizationEntity getByIdOrElseThrow(Long id,ResultCode errorCode){
    return organizationRepository.findById(id).orElseThrow(() -> new BizException(errorCode));
}


public Map<Long,OrganizationEntity> getIdEntityMap(){
    List<OrganizationEntity> organizations = organizationRepository.findAll();
    return organizations.stream().collect(Collectors.toMap(OrganizationEntity::getId, organization -> organization));
}


public void validateOrgCode(Long id,String code,OrganizationEnums organizationType){
    Optional<OrganizationEntity> organizationEntityOptional = organizationRepository.findByCodeAndType(code, organizationType);
    if (organizationEntityOptional.isPresent()) {
        OrganizationEntity organization = organizationEntityOptional.get();
        if (!organization.getId().equals(id)) {
            throw new BizException(ResultCode.ORG_CODE_EXISTS);
        }
    }
}


public List<Long> listAllPermissionOrgIds(PlatformType platformType,MenuCode menuCode,ActionType actionType){
    List<Long> orgIds = TenantThreadHelper.getPermissionOrgIds(platformType, menuCode, actionType);
    return listSelfAndChildrenIds(orgIds);
}


public List<String> listOrganizationPathByIds(List<Long> ids){
    List<OrganizationEntity> organizations = organizationRepository.findAllById(ids);
    List<String> orgPaths = Lists.newArrayListWithExpectedSize(organizations.size());
    for (OrganizationEntity organization : organizations) {
        orgPaths.add(organization.getPath());
    }
    return orgPaths;
}


public List<Long> listSelfAndChildrenIds(List<Long> ids){
    Specification<OrganizationEntity> specification = getOrgPathLikeSpecification(ids);
    List<OrganizationEntity> organizations = organizationRepository.findAll(specification);
    List<Long> orgIds = Lists.newArrayListWithExpectedSize(organizations.size());
    for (OrganizationEntity organization : organizations) {
        orgIds.add(organization.getId());
    }
    return orgIds;
}


public Specification<OrganizationEntity> getOrgPathLikeSpecification(List<Long> ids){
    List<String> orgPaths = listOrganizationPathByIds(ids);
    return (root, query, criteriaBuilder) -> {
        Predicate[] predicates = new Predicate[orgPaths.size()];
        for (int i = 0; i < orgPaths.size(); i++) {
            String orgPath = orgPaths.get(i) + "%";
            predicates[i] = criteriaBuilder.like(root.get("path"), orgPath);
        }
        return criteriaBuilder.or(predicates);
    };
}


public Map<Long,String> getIdNameMap(){
    List<OrganizationEntity> organizations = organizationRepository.findAll();
    return organizations.stream().collect(Collectors.toMap(OrganizationEntity::getId, OrganizationEntity::getName));
}


@Transactional
public void createOrganizationPath(OrganizationEntity organization){
    Optional<OrganizationEntity> parent = organizationRepository.findById(organization.getParentId());
    String path = (parent.isPresent() ? parent.get().getPath() : "") + organization.getId() + SystemConstant.PATH_SEPARATOR;
    organization.setPath(path);
    organizationRepository.save(organization);
}


@Transactional
public void updateOrganizationPath(OrganizationEntity organizationEntity){
    if (organizationEntity.getParentId() > 0) {
        String path = organizationEntity.getPath();
        List<OrganizationEntity> organizations = organizationRepository.findAllByPathLike(path + "%");
        Map<Long, OrganizationEntity> map = getIdEntityMap();
        for (OrganizationEntity organization : organizations) {
            int index = organization.getPath().indexOf(organization.getId() + SystemConstant.PATH_SEPARATOR);
            String newPath = map.get(organization.getParentId()).getPath() + organization.getPath().substring(index);
            organization.setPath(newPath);
        }
        organizationRepository.saveAll(organizations);
    }
}


}