package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersPatentService {

   public Page<CreepersPatentDTO> queryPatentList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType);
}