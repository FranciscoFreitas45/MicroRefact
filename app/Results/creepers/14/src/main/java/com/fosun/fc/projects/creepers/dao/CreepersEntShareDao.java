package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersEntShare;
public interface CreepersEntShareDao extends JpaRepository<TCreepersEntShare, Long>, JpaSpecificationExecutor<TCreepersEntShare>{


}