package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersMerBasic;
public interface CreepersMerBasicDao extends JpaSpecificationExecutor<TCreepersMerBasic>, JpaRepository<TCreepersMerBasic, Long>{


}