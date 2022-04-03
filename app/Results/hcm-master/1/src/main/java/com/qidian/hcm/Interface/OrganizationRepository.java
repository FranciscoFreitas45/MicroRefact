package com.qidian.hcm.Interface;
public interface OrganizationRepository {

   public Object findById(Object Object);
   public List<OrganizationEntity> findAllDisabledDepartmentByPositionIdIn(List<Long> ids);
}