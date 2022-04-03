package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersEntIntel;
public interface CreepersEntIntelDao extends JpaRepository<TCreepersEntIntel, Long>, JpaSpecificationExecutor<TCreepersEntIntel>{


}