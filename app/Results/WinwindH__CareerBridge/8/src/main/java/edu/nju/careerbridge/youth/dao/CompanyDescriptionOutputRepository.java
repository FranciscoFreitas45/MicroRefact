package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import edu.nju.careerbridge.youth.model.CompanyDescriptionOutput;
import edu.nju.careerbridge.youth.model.CompanyInterviewRemark;
import javax.transaction.Transactional;
import java.util.List;
public interface CompanyDescriptionOutputRepository extends JpaRepository<CompanyDescriptionOutput, Integer>{


@Transactional
public void deleteByCompanyName(String companyName)
;

public List<CompanyDescriptionOutput> findByCompanyName(String companyName)
;

}