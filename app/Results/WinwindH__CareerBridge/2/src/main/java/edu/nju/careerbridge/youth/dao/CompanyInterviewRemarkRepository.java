package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.CompanyInterviewRemark;
import edu.nju.careerbridge.youth.model.CompanyRemark;
import javax.transaction.Transactional;
public interface CompanyInterviewRemarkRepository extends JpaRepository<CompanyInterviewRemark, Integer>{


public CompanyInterviewRemark findByCompany(String company)
;

@Transactional
public void deleteByCompany(String company)
;

}