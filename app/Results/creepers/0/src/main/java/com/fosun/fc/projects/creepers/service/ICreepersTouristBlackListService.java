package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;
public interface ICreepersTouristBlackListService extends BaseService{


public List<TCreepersTourBlackList> findListByGuideNo(String merName)
;

public List<TCreepersTourBlackList> findListByAgentNameAndType(String merName)
;

public void saveEntity(TCreepersTourBlackList entity)
;

public void processByJob(String jobName)
;

}