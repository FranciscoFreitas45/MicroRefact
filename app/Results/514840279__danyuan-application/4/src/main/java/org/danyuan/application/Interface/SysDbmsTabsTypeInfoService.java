package org.danyuan.application.Interface;
public interface SysDbmsTabsTypeInfoService {

   public List<SysDbmsTabsTypeInfo> findAllTypeByUser(String username);
   public List<SysDbmsTabsTypeInfo> findAll();
}