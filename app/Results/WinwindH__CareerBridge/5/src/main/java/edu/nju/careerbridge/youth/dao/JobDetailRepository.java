package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.Honor;
import edu.nju.careerbridge.youth.model.JobDetail;
import javax.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.JobDetail;
import edu.nju.careerbridge.youth.model.ProjectExperience;
public interface JobDetailRepository extends JpaRepository<JobDetail, Integer>{


public JobDetail findByJobId(String jobId)
;

@Query(value = "SELECT * FROM job2_0 WHERE job2_0.job_id in (SELECT DISTINCT job_like.job_id from job_like WHERE phone=:ph)", nativeQuery = true)
public List<JobDetail> getSavedJob(String phone)
;

}