package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDisInfo;
public interface CreepersCourtDisInfoDao extends JpaRepository<TCreepersCourtDisInfo, Long>, JpaSpecificationExecutor<TCreepersCourtDisInfo>{


@Query("select t from TCreepersCourtDisInfo t where t.name = :name")
public List<TCreepersCourtDisInfo> findByName(String name)
;

@Modifying(clearAutomatically = true)
@Query("delete  from TCreepersCourtDisInfo t where t.name = :name")
public void deleteByName(String name)
;

}