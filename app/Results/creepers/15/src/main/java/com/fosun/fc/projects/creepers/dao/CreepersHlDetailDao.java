package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
public interface CreepersHlDetailDao extends JpaRepository<TCreepersHlDetail, Long>, JpaSpecificationExecutor<TCreepersHlDetail>{


@Query("select t from TCreepersHlDetail t where t.rptNo = :rptNo")
public List<TCreepersHlDetail> queryByRptNo(String rptNo)
;

public TCreepersHlDetail addTCreepersHlDetail(Long id,TCreepersHlDetail TCreepersHlDetail);

public List<TCreepersHlDetail> getTCreepersHlDetails(Long id);

public void setTCreepersHlDetails(Long id,List<TCreepersHlDetail> TCreepersHlDetails);

public TCreepersHlDetail removeTCreepersHlDetail(Long id,TCreepersHlDetail TCreepersHlDetail);

}