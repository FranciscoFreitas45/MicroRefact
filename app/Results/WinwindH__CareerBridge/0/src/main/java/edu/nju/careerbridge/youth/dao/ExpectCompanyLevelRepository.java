package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.ExpectCompanyLevel;
import edu.nju.careerbridge.youth.model.ExpectCompanyQuality;
import javax.transaction.Transactional;
import java.util.List;
public interface ExpectCompanyLevelRepository extends JpaRepository<ExpectCompanyLevel, Integer>{


public List<ExpectCompanyLevel> findByPhone(String phone)
;

@Transactional
public void deleteByPhone(String phone)
;

}