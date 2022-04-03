package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.bean.JobExperienceBean;
import edu.nju.careerbridge.youth.model.JobExperience;
import javax.transaction.Transactional;
import java.util.List;
public interface JobExperienceRepository extends JpaRepository<JobExperience, Integer>{


public List<JobExperience> findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

}