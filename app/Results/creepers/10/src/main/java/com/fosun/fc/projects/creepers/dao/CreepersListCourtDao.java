package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersListCourt;
public interface CreepersListCourtDao extends JpaSpecificationExecutor<TCreepersListCourt>, JpaRepository<TCreepersListCourt, Long>{


@Query("select t from TCreepersListCourt t where t.merName = :merName")
public List<TCreepersListCourt> queryListByMerName(String merName)
;

@Modifying(clearAutomatically = true)
@Query("update TCreepersListCourt t set t.flag = :flag, t.updatedDt = sysdate where t.merName = :merName")
public void updateListByMerName(String merName,String flag)
;

}