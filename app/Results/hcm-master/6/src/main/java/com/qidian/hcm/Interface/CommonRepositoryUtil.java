package com.qidian.hcm.Interface;
public interface CommonRepositoryUtil {

   public Page<T> pageByNative(Class clazz,String querySql,String countSql,Map<String,Object> params,int pageNo,int pageSize);
}