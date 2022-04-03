package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
public interface CreepersPublicEnforcementDao extends JpaRepository<TCreepersPublicEnforcement, Long>, JpaSpecificationExecutor<TCreepersPublicEnforcement>{


@Query("select t from TCreepersPublicEnforcement t where t.rptNo = :rptNo")
public List<TCreepersPublicEnforcement> queryByRptNo(String rptNo)
;

public void setTCreepersPublicEnforcements(Long id,List<TCreepersPublicEnforcement> TCreepersPublicEnforcements);

public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(Long id);

public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(Long id,TCreepersPublicEnforcement TCreepersPublicEnforcement);

public TCreepersPublicEnforcement addTCreepersPublicEnforcement(Long id,TCreepersPublicEnforcement TCreepersPublicEnforcement);

}