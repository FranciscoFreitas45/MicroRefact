package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.Education;
import edu.nju.careerbridge.youth.model.JobVector;
import javax.transaction.Transactional;
public interface JobVectorRepository extends JpaSpecificationExecutor<JobVector>, JpaRepository<JobVector, Integer>{


public JobVector findByJobCity(String jobCity)
;

}