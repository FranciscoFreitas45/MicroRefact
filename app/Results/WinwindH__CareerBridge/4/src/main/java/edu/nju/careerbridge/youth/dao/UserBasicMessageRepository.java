package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.bean.JobExperienceBean;
import edu.nju.careerbridge.youth.bean.UserBasicMessageBean;
import edu.nju.careerbridge.youth.model.JobExperience;
import edu.nju.careerbridge.youth.model.UserBasicMessage;
import javax.transaction.Transactional;
import java.util.List;
public interface UserBasicMessageRepository extends JpaRepository<UserBasicMessage, Integer>{


public UserBasicMessage findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

}