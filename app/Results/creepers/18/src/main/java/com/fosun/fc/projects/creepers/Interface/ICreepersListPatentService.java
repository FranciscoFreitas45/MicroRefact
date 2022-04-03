package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersListPatentService {

   public Page<CreepersListPatentDTO> queryListPatentList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType);
}