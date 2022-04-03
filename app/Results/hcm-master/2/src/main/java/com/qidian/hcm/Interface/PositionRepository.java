package com.qidian.hcm.Interface;
public interface PositionRepository {

   public List<PositionEntity> findAllByDepartmentIdInAndEnableAndDeleted(List<Long> departmentIds,YesNo enable,YesNo deleted);
   public List<PositionEntity> findAllByDepartmentIdAndEnableAndDeleted(Long departmentId,YesNo enable,YesNo deleted);
}