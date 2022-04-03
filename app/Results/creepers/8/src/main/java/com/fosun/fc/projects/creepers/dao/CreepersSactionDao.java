package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersSaction;
public interface CreepersSactionDao extends JpaRepository<TCreepersSaction, Long>, JpaSpecificationExecutor<TCreepersSaction>{


@Query("select t from TCreepersSaction t where t.name = :name")
public List<TCreepersSaction> findByName(String name)
;

@Modifying(clearAutomatically = true)
@Query("delete  from TCreepersSaction t where t.name = :name")
public void deleteByName(String name)
;

public TCreepersSaction findTopByName(String name)
;

}