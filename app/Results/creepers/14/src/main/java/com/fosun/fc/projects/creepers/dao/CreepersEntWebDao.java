package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersEntWeb;
public interface CreepersEntWebDao extends JpaRepository<TCreepersEntWeb, Long>, JpaSpecificationExecutor<TCreepersEntWeb>{


}