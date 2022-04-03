package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
public interface CreepersOlDetailDao extends JpaSpecificationExecutor<TCreepersOlDetail>, JpaRepository<TCreepersOlDetail, Long>{


@Query("select t from TCreepersOlDetail t where t.rptNo = :rptNo")
public List<TCreepersOlDetail> queryByRptNo(String rptNo)
;

public List<TCreepersOlDetail> getTCreepersOlDetails(Long id);

public TCreepersOlDetail removeTCreepersOlDetail(Long id,TCreepersOlDetail TCreepersOlDetail);

public void setTCreepersOlDetails(Long id,List<TCreepersOlDetail> TCreepersOlDetails);

public TCreepersOlDetail addTCreepersOlDetail(Long id,TCreepersOlDetail TCreepersOlDetail);

}