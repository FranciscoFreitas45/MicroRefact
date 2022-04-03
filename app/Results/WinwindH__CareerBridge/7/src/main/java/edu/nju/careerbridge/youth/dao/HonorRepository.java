package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.Honor;
import edu.nju.careerbridge.youth.model.JobExperience;
import javax.transaction.Transactional;
import java.util.List;
public interface HonorRepository extends JpaRepository<Honor, Integer>{


public List<Honor> findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

public void setPhone(Integer id,String phone);

public void setHonorName(Integer id,String honorName);

}