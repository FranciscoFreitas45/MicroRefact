package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersJudgementService {

   public Page<CreepersJudgementDTO> queryJudgementList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType);
}