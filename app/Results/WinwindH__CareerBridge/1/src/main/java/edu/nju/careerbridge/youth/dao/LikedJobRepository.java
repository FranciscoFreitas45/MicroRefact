package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import edu.nju.careerbridge.youth.model.JobExperience;
import edu.nju.careerbridge.youth.model.LikedJob;
import javax.transaction.Transactional;
public interface LikedJobRepository extends JpaRepository<LikedJob, Integer>{


@Transactional
public int deleteByPhoneAndJobId(String phone,String jobId)
;

}