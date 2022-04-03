package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersShixin;
public interface CreepersShixinDao extends JpaSpecificationExecutor<TCreepersShixin>, JpaRepository<TCreepersShixin, Long>{


@Query("select t from TCreepersShixin t where t.name = :name")
public List<TCreepersShixin> findByName(String name)
;

@Modifying(clearAutomatically = true)
@Query("delete  from TCreepersShixin t where t.name = :name")
public void deleteByName(String name)
;

public TCreepersShixin findTopByName(String name)
;

}