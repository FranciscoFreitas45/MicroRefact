package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersEntShareChange;
public interface CreepersEntShareChangeDao extends JpaRepository<TCreepersEntShareChange, Long>, JpaSpecificationExecutor<TCreepersEntShareChange>{


}