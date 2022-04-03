package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersPatent;
public interface CreepersPatentDao extends JpaRepository<TCreepersPatent, Long>, JpaSpecificationExecutor<TCreepersPatent>{


@Modifying
@Query("delete  from TCreepersPatent t where t.merName = :merName")
public void deleteByMerName(String merName)
;

@Query("select t from TCreepersPatent t where t.merName = :merName")
public List<TCreepersPatent> findByMerName(String merName)
;

}