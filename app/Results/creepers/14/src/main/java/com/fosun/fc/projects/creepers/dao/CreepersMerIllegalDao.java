package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersMerIllegal;
public interface CreepersMerIllegalDao extends JpaRepository<TCreepersMerIllegal, Long>, JpaSpecificationExecutor<TCreepersMerIllegal>{


}