package com.uec.imonitor.config.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
public interface IConfigPageParamJPARepository extends JpaRepository<ConfigPageParamEntity, Integer>{


@Query("from ConfigPageParamEntity a where a.name = :name ")
public ConfigPageParamEntity findByName(String name)
;

}