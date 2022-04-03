package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
public interface CreepersPublicIspDao extends JpaRepository<TCreepersPublicIsp, Long>, JpaSpecificationExecutor<TCreepersPublicIsp>{


@Query("select t from TCreepersPublicIsp t where t.rptNo = :rptNo")
public List<TCreepersPublicIsp> queryByRptNo(String rptNo)
;

public TCreepersPublicIsp addTCreepersPublicIsp(Long id,TCreepersPublicIsp TCreepersPublicIsp);

public List<TCreepersPublicIsp> getTCreepersPublicIsps(Long id);

public TCreepersPublicIsp removeTCreepersPublicIsp(Long id,TCreepersPublicIsp TCreepersPublicIsp);

public void setTCreepersPublicIsps(Long id,List<TCreepersPublicIsp> TCreepersPublicIsps);

}