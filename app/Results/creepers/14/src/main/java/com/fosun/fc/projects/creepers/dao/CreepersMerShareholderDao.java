package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersMerShareholder;
public interface CreepersMerShareholderDao extends JpaRepository<TCreepersMerShareholder, Long>, JpaSpecificationExecutor<TCreepersMerShareholder>{


}