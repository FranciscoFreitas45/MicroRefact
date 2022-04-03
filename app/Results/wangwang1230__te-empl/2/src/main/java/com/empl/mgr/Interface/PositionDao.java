package com.empl.mgr.Interface;
public interface PositionDao {

   public Object findUniqueByProperty(Object Object);
   public List<PositionDto> findByDeptId(long deptId);
}