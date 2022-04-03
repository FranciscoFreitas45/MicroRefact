package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersListJudgement;
public interface CreepersListJudgementDao extends JpaRepository<TCreepersListJudgement, Long>, JpaSpecificationExecutor<TCreepersListJudgement>{


@Query("select t from TCreepersListJudgement t where t.merName = :merName")
public List<TCreepersListJudgement> queryListByMerName(String merName)
;

@Modifying(clearAutomatically = true)
@Query("update TCreepersListJudgement t set t.flag = :flag, t.updatedDt = sysdate where t.merName = :merName")
public void updateListByMerName(String merName,String flag)
;

}