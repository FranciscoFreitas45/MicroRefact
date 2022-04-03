package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;
public interface CreepersPublicCivilDao extends JpaRepository<TCreepersPublicCivil, Long>, JpaSpecificationExecutor<TCreepersPublicCivil>{


@Query("select t from TCreepersPublicCivil t where t.rptNo = :rptNo")
public List<TCreepersPublicCivil> queryByRptNo(String rptNo)
;

public TCreepersPublicCivil addTCreepersPublicCivil(Long id,TCreepersPublicCivil TCreepersPublicCivil);

public TCreepersPublicCivil removeTCreepersPublicCivil(Long id,TCreepersPublicCivil TCreepersPublicCivil);

public List<TCreepersPublicCivil> getTCreepersPublicCivils(Long id);

public void setTCreepersPublicCivils(Long id,List<TCreepersPublicCivil> TCreepersPublicCivils);

}