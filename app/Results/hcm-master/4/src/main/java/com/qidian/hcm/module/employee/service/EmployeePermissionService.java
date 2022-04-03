package com.qidian.hcm.module.employee.service;
 import com.qidian.hcm.module.authorization.dto.ConditionDTO;
import com.qidian.hcm.module.authorization.dto.PermissionDTO;
import com.qidian.hcm.module.authorization.dto.PermissionMetaDataDTO;
import com.qidian.hcm.module.authorization.dto.RolePermissionDTO;
import com.qidian.hcm.module.authorization.entity.EmployeeRole;
import com.qidian.hcm.module.authorization.repository.EmployeeRoleRepository;
import com.qidian.hcm.module.authorization.service.RoleService;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.qidian.hcm.Interface.OrganizationService;
@Service
public class EmployeePermissionService {

@Autowired
 private  RoleService roleService;

@Autowired
 private  EmployeeRoleRepository employeeRoleRepository;

@Autowired
 private  OrganizationService organizationService;


@Transactional
public void editEmployeeRole(Long employeeId,List<Long> roleIds){
    employeeRoleRepository.deleteAllByEmployeeId(employeeId);
    List<EmployeeRole> employeeRoles = roleIds.stream().map(e -> {
        roleService.validateRoleId(e);
        return new EmployeeRole(employeeId, e);
    }).collect(Collectors.toList());
    employeeRoleRepository.saveAll(employeeRoles);
}


public RolePermissionDTO getEmployeePermission(Long employeeId){
    List<EmployeeRole> employeeRoles = employeeRoleRepository.findAllByEmployeeId(employeeId);
    if (employeeRoles.isEmpty()) {
        return null;
    }
    List<Long> roleIds = employeeRoles.stream().map(EmployeeRole::getRoleId).collect(Collectors.toList());
    Map<Long, RolePermissionDTO> rolePermissionDTOMap = roleService.getRolePermissionDTOMap(roleIds);
    Map<Long, OrganizationEntity> organizationMap = organizationService.getIdEntityMap();
    RolePermissionDTO firstRolePermissionDTO = rolePermissionDTOMap.get(roleIds.remove(0));
    for (Long roleId : roleIds) {
        RolePermissionDTO rolePermissionDTO = rolePermissionDTOMap.get(roleId);
        if (rolePermissionDTO == null) {
            continue;
        }
        mergePermissionData(firstRolePermissionDTO.getBackend(), rolePermissionDTO.getBackend(), organizationMap);
        mergePermissionData(firstRolePermissionDTO.getFrontend(), rolePermissionDTO.getFrontend(), organizationMap);
    }
    return firstRolePermissionDTO;
}


public void mergePermissionData(List<PermissionMetaDataDTO> permissionData1,List<PermissionMetaDataDTO> permissionData2,Map<Long,OrganizationEntity> organizationMap){
    for (PermissionMetaDataDTO pd1 : permissionData1) {
        Iterator<PermissionMetaDataDTO> iterator = permissionData2.iterator();
        while (iterator.hasNext()) {
            PermissionMetaDataDTO pd2 = iterator.next();
            // 菜单编号相同
            if (pd1.getCode() != pd2.getCode()) {
                continue;
            }
            for (PermissionDTO permissionDTO1 : pd1.getPermissions()) {
                for (PermissionDTO permissionDTO2 : pd2.getPermissions()) {
                    // 操作相同
                    if (permissionDTO1.getAction() != permissionDTO2.getAction()) {
                        continue;
                    }
                    List<ConditionDTO> conditions1 = permissionDTO1.getConditions();
                    List<ConditionDTO> conditions2 = permissionDTO2.getConditions();
                    if (CollectionUtils.isEmpty(conditions1) || CollectionUtils.isEmpty(conditions2)) {
                        continue;
                    }
                    String orgId1 = conditions1.get(0).getValue();
                    String orgId2 = conditions2.get(0).getValue();
                    OrganizationEntity organization1 = organizationMap.get(Long.valueOf(orgId1));
                    OrganizationEntity organization2 = organizationMap.get(Long.valueOf(orgId2));
                    if (organization2.getPath().indexOf(organization1.getPath()) > 0) {
                        permissionDTO1.setConditions(conditions1);
                    } else if (organization1.getPath().indexOf(organization2.getPath()) > 0) {
                        permissionDTO1.setConditions(conditions2);
                    } else {
                        conditions1.addAll(conditions2);
                        permissionDTO1.setConditions(conditions1);
                    }
                }
            }
            iterator.remove();
        }
    }
    permissionData1.addAll(permissionData2);
}


public void deleteEmployeeRole(Employee employee){
    employeeRoleRepository.deleteAllByEmployeeId(employee.getId());
}


}