package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;
public interface CreepersTourBlackListDao extends JpaRepository<TCreepersTourBlackList, Long>, JpaSpecificationExecutor<TCreepersTourBlackList>{


public List<TCreepersTourBlackList> findByAgentNameAndType(String merName,String type)
;

public TCreepersTourBlackList findTop1ByGuideNo(String guideNo)
;

public List<TCreepersTourBlackList> findByGuideNo(String guideNo)
;

}