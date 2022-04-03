package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;
public interface CreepersCompensatoryDao extends JpaSpecificationExecutor<TCreepersCompensatory>, JpaRepository<TCreepersCompensatory, Long>{


@Query("select t from TCreepersCompensatory t where t.rptNo = :rptNo")
public List<TCreepersCompensatory> queryByRptNo(String rptNo)
;

public void setTCreepersCompensatories(Long id,List<TCreepersCompensatory> TCreepersCompensatories);

public TCreepersCompensatory removeTCreepersCompensatory(Long id,TCreepersCompensatory TCreepersCompensatory);

public TCreepersCompensatory addTCreepersCompensatory(Long id,TCreepersCompensatory TCreepersCompensatory);

public List<TCreepersCompensatory> getTCreepersCompensatories(Long id);

}