package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersGeneral;
public interface CreepersGeneralDao extends JpaRepository<TCreepersGeneral, Long>, JpaSpecificationExecutor<TCreepersGeneral>{


@Query("select t from TCreepersGeneral t where t.rptNo = :rptNo")
public List<TCreepersGeneral> queryByRptNo(String rptNo)
;

}