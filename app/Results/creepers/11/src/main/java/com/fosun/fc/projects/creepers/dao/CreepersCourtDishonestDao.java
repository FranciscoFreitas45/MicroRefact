package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDishonest;
public interface CreepersCourtDishonestDao extends JpaRepository<TCreepersCourtDishonest, Long>, JpaSpecificationExecutor<TCreepersCourtDishonest>{


@Query("select t from TCreepersCourtDishonest t where t.name = :name and t.code = :code")
public List<TCreepersCourtDishonest> findListByNameAndCode(String name,String code)
;

@Query("select t from TCreepersCourtDishonest t where t.name = :name")
public List<TCreepersCourtDishonest> findByName(String name)
;

@Modifying(clearAutomatically = true)
@Query("delete  from TCreepersCourtDishonest t where t.name = :name")
public void deleteByName(String name)
;

public TCreepersCourtDishonest findTopByName(String name)
;

}