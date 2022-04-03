package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersShixinService {

   public Page<CreepersShixinDTO> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType);
}