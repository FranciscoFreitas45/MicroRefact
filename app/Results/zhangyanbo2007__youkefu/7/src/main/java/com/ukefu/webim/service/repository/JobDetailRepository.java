package com.ukefu.webim.service.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.JobDetail;
public interface JobDetailRepository extends JpaRepository<JobDetail, String>{


public Page<JobDetail> findByTaskstatus(String taskstatus,Pageable page)
;

public List<JobDetail> findByOrgiAndTasktypeAndOrganLike(String orgi,String tasktype,String organ)
;

public List<JobDetail> findByOrgiAndExtention(String orgi,String extention)
;

public Page<JobDetail> findByPlantaskAndTaskstatusAndNextfiretimeLessThan(boolean plantask,String taskstatus,Date time,Pageable page)
;

public List<JobDetail> findByTasktypeAndOrgi(String tasktype,String orgi)
;

public JobDetail findByIdAndOrgi(String id,String orgi)
;

public List<JobDetail> findAll(Specification<JobDetail> spec)
;

public List<JobDetail> findByOrgiAndBatchtypeAndOrganid(String orgi,String batchtype,String organ)
;

public Page<JobDetail> findByOrgiAndForecastid(String orgi,String forecastid,Pageable page)
;

}