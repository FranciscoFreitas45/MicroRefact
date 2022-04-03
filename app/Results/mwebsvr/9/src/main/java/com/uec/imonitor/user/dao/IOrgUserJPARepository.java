package com.uec.imonitor.user.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.user.bean.OrgUserEntity;
public interface IOrgUserJPARepository extends JpaRepository<OrgUserEntity, Integer>{


@Query("from OrgUserEntity a where a.userId = :userId")
public List<OrgUserEntity> findByUserId(Integer userId)
;

@Query("from OrgUserEntity a where a.orgId = :orgId")
public List<OrgUserEntity> findByOrgId(Integer orgId)
;

}