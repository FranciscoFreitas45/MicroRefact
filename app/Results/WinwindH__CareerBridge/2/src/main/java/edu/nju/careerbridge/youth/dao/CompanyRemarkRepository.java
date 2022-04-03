package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.CompanyRemark;
import edu.nju.careerbridge.youth.model.Education;
import javax.transaction.Transactional;
public interface CompanyRemarkRepository extends JpaRepository<CompanyRemark, Integer>{


public CompanyRemark findByCompany(String company)
;

@Transactional
public void deleteByCompany(String company)
;

}