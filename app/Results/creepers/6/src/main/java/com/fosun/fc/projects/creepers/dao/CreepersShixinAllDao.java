package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersShixinAll;
public interface CreepersShixinAllDao extends JpaRepository<TCreepersShixinAll, Long>, JpaSpecificationExecutor<TCreepersShixinAll>{


public List<TCreepersShixinAll> findByName(String name)
;

public TCreepersShixinAll findTopByName(String name)
;

}