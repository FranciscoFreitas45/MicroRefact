package com.uec.imonitor.task.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.task.bean.JobFailureEntity;
public interface IJobFailureJPARepository extends JpaRepository<JobFailureEntity, Integer>{


@Query("from JobFailureEntity a where a.innerid = :innerid")
public JobFailureEntity findByInnerId(Integer innerid)
;

@Query(value = "select * from job_failure  a where a.job_name = :jobName and a.table_name = :tableName   order by innerid desc limit :topN", nativeQuery = true)
public List<JobFailureEntity> listTopNByJobAndTableName(String jobName,String tableName,Integer topN)
;

@Query("from JobFailureEntity a where a.jobName = :jobName and a.tableName = :tableName")
public List<JobFailureEntity> listByJobAndTableName(String jobName,String tableName)
;

@Query("from JobFailureEntity a where a.jobName = :jobName")
public List<JobFailureEntity> listByJobName(String jobName)
;

}