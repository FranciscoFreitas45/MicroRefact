package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;
public interface CreepersCourtAnnounceDao extends JpaRepository<TCreepersCourtAnnounce, Long>, JpaSpecificationExecutor<TCreepersCourtAnnounce>{


@Modifying
@Query("delete  from TCreepersCourtAnnounce t where t.merName = :merName")
public void deleteByMerName(String merName)
;

@Query("select t from TCreepersCourtAnnounce t where t.merName =:merName")
public List<TCreepersCourtAnnounce> findByMerName(String merName)
;

}