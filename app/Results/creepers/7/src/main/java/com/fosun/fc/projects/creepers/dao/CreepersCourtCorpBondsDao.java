package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;
public interface CreepersCourtCorpBondsDao extends JpaRepository<TCreepersCourtCorpBonds, Long>, JpaSpecificationExecutor<TCreepersCourtCorpBonds>{


@Query("select t from TCreepersCourtCorpBonds t where t.name = :name and t.code = :code")
public List<TCreepersCourtCorpBonds> findListByNameAndCode(String name,String code)
;

@Query("select t from TCreepersCourtCorpBonds t where t.name = :name")
public List<TCreepersCourtCorpBonds> findByName(String name)
;

@Modifying(clearAutomatically = true)
@Query("delete  from TCreepersCourtCorpBonds t where t.name = :name")
public void deleteByName(String name)
;

public TCreepersCourtCorpBonds findTopByName(String name)
;

}