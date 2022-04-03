package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersJob;
public interface CreepersJobDao extends JpaSpecificationExecutor<TCreepersJob>, JpaRepository<TCreepersJob, Long>{


@Modifying(clearAutomatically = true)
@Query("update TCreepersJob t set t.memo = :request, t.updatedDt = sysdate where t.jobName = :jobName")
public void updateRequestByJobName(String jobName,String request)
;

public long countByJobName(String jobName)
;

@Modifying(clearAutomatically = true)
@Query("update TCreepersJob t set t.cronExpression = :cronExpression, t.updatedDt = sysdate where t.jobName = :jobName and t.jobGroup = :jobGroup")
public void updateCronExpByJobName(String cronExpression,String jobName,String jobGroup)
;

@Modifying(clearAutomatically = true)
@Query("update TCreepersJob t set t.status = :status, t.updatedDt = sysdate where t.jobName = :jobName and t.jobGroup = :jobGroup")
public void updateStatusByJobName(String status,String jobName,String jobGroup)
;

@Query("select t from TCreepersJob t where t.status != 'NONE'")
public List<TCreepersJob> findAllUseJobs()
;

public TCreepersJob findTop1ByJobName(String jobName)
;

}