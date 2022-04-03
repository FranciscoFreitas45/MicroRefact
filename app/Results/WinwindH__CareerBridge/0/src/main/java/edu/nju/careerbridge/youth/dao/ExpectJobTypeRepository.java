package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.ExpectJobType;
import edu.nju.careerbridge.youth.model.ExpectLocation;
import javax.transaction.Transactional;
import java.util.List;
public interface ExpectJobTypeRepository extends JpaRepository<ExpectJobType, Integer>{


public List<ExpectJobType> findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

}