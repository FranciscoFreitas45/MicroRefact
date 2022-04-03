package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersBasic;
public interface CreepersBasicDao extends JpaSpecificationExecutor<TCreepersBasic>, JpaRepository<TCreepersBasic, Long>{


@Query("select t from TCreepersBasic t where t.name = :name")
public List<TCreepersBasic> queryByName(String name)
;

@Query("select t from TCreepersBasic t where t.rptNo = :rptNo")
public List<TCreepersBasic> queryByRptNo(String rptNo)
;

@Query("select t from TCreepersBasic t where t.idNo = :idNo")
public List<TCreepersBasic> queryByIdNo(String idNo)
;

public List<TCreepersBasic> getTCreepersBasics(Long id);

public TCreepersBasic addTCreepersBasic(Long id,TCreepersBasic TCreepersBasic);

public void setTCreepersBasics(Long id,List<TCreepersBasic> TCreepersBasics);

public TCreepersBasic removeTCreepersBasic(Long id,TCreepersBasic TCreepersBasic);

}