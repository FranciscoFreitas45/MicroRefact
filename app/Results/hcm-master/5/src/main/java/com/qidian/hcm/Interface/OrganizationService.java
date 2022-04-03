package com.qidian.hcm.Interface;
public interface OrganizationService {

   public Map<Long,String> getIdNameMap();
   public List<String> listOrganizationPathByIds(List<Long> ids);
   public void createOrganizationPath(OrganizationEntity organization);
}