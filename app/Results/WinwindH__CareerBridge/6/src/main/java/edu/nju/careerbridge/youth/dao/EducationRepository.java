package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.Education;
import edu.nju.careerbridge.youth.model.JobExperience;
import javax.transaction.Transactional;
import java.util.List;
public interface EducationRepository extends JpaRepository<Education, Integer>{


public Education findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

}