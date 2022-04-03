package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersFundExtraDetail;
public interface CreepersFundExtraDetailDao extends JpaSpecificationExecutor<TCreepersFundExtraDetail>, JpaRepository<TCreepersFundExtraDetail, Long>{


@Query("select t from TCreepersFundExtraDetail t where t.loginName =:loginName")
public List<TCreepersFundExtraDetail> findByLoginName(String loginName)
;

@Modifying
@Query("delete  from TCreepersFundExtraDetail t where t.loginName = :loginName")
public void deleteByLoginName(String loginName)
;

}