package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
public interface CreepersGuaranteeDao extends JpaSpecificationExecutor<TCreepersGuarantee>, JpaRepository<TCreepersGuarantee, Long>{


@Query("select t from TCreepersGuarantee t where t.rptNo = :rptNo")
public List<TCreepersGuarantee> queryByRptNo(String rptNo)
;

public void setTCreepersGuarantees(Long id,List<TCreepersGuarantee> TCreepersGuarantees);

public TCreepersGuarantee addTCreepersGuarantee(Long id,TCreepersGuarantee TCreepersGuarantee);

public List<TCreepersGuarantee> getTCreepersGuarantees(Long id);

public TCreepersGuarantee removeTCreepersGuarantee(Long id,TCreepersGuarantee TCreepersGuarantee);

}