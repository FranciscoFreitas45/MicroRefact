package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.CompanyDescriptionOutput;
import edu.nju.careerbridge.youth.model.JobDescriptionOutput;
import javax.transaction.Transactional;
import java.util.List;
public interface JobDescriptionOutputRepository extends JpaRepository<JobDescriptionOutput, Integer>{


public List<JobDescriptionOutput> findByJobId(String jobId)
;

@Transactional
public void deleteByJobId(String jobId)
;

}