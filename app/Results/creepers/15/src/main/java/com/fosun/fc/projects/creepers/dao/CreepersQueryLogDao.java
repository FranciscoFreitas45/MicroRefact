package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersQueryLog;
public interface CreepersQueryLogDao extends JpaRepository<TCreepersQueryLog, Long>, JpaSpecificationExecutor<TCreepersQueryLog>{


@Query("select t from TCreepersQueryLog t where t.rptNo = :rptNo")
public List<TCreepersQueryLog> queryByRptNo(String rptNo)
;

}