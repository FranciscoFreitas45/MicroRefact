package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersFundBasic;
public interface CreepersFundBasicDao extends JpaRepository<TCreepersFundBasic, Long>, JpaSpecificationExecutor<TCreepersFundBasic>{


@Query("select t from TCreepersFundBasic t where t.loginName =:loginName")
public List<TCreepersFundBasic> findByLoginName(String loginName)
;

@Modifying
@Query("delete  from TCreepersFundBasic t where t.loginName = :loginName")
public void deleteByLoginName(String loginName)
;

}