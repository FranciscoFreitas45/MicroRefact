package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.ExpectCompanyQuality;
import edu.nju.careerbridge.youth.model.ExpectLocation;
import javax.transaction.Transactional;
import java.util.List;
public interface ExpectCompanyQualityRepository extends JpaRepository<ExpectCompanyQuality, Integer>{


public List<ExpectCompanyQuality> findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

}