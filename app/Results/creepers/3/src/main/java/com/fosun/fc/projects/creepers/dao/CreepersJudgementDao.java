package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;
public interface CreepersJudgementDao extends JpaRepository<TCreepersJudgement, Long>, JpaSpecificationExecutor<TCreepersJudgement>{


@Query("select t from TCreepersJudgement t where t.merName = :merName")
public List<TCreepersJudgement> queryByMerName(String merName)
;

@Modifying
@Query("delete  from TCreepersJudgement t where t.merName = :merName")
public void deleteByMerName(String merName)
;

}