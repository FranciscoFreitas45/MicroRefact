package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;
public interface CreepersTradeMarkDao extends JpaSpecificationExecutor<TCreepersTradeMark>, JpaRepository<TCreepersTradeMark, Long>{


@Modifying
@Query("delete  from TCreepersTradeMark t where t.merName = :merName")
public void deleteByMerName(String merName)
;

@Query("select t from TCreepersTradeMark t where t.merName = :merName")
public List<TCreepersTradeMark> findByMerName(String merName)
;

}