package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;
public interface CreepersConfigDao extends JpaSpecificationExecutor<TCreepersConfig>, JpaRepository<TCreepersConfig, Long>{


public TCreepersConfig findTop1ByRequestType(String requestType)
;

public long countByRequestType(String requestType)
;

}