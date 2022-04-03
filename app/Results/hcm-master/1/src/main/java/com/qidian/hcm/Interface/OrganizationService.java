package com.qidian.hcm.Interface;
public interface OrganizationService {

   public List<Long> listSelfAndChildrenIds(List<Long> ids);
   public Map<Long,OrganizationEntity> getIdEntityMap();
   public List<Long> listAllPermissionOrgIds(PlatformType platformType,MenuCode menuCode,ActionType actionType);
}