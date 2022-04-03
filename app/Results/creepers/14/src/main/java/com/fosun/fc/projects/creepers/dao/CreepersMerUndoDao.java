package com.fosun.fc.projects.creepers.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersMerUndo;
public interface CreepersMerUndoDao extends JpaSpecificationExecutor<TCreepersMerUndo>, JpaRepository<TCreepersMerUndo, Long>{


}