package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersFundBasicDetail;
public interface CreepersFundBasicDetailDao extends JpaRepository<TCreepersFundBasicDetail, Long>, JpaSpecificationExecutor<TCreepersFundBasicDetail>{


@Query("select t from TCreepersFundBasicDetail t where t.loginName =:loginName")
public List<TCreepersFundBasicDetail> findByLoginName(String loginName)
;

@Modifying
@Query("delete  from TCreepersFundBasicDetail t where t.loginName = :loginName")
public void deleteByLoginName(String loginName)
;

}