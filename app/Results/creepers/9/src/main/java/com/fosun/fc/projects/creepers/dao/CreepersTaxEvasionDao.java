package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasion;
public interface CreepersTaxEvasionDao extends JpaRepository<TCreepersTaxEvasion, Long>, JpaSpecificationExecutor<TCreepersTaxEvasion>{


public List<TCreepersTaxEvasion> findByName(String name)
;

public TCreepersTaxEvasion findTopByName(String name)
;

}