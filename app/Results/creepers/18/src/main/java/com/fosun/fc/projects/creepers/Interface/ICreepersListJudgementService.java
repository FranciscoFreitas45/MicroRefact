package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersListJudgementService {

   public Page<CreepersListJudgementDTO> findListJudgementList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType);
}